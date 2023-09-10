package com.github.supercoding.service;

import com.github.supercoding.repository.airlineTicket.AirlineTicket;
import com.github.supercoding.repository.airlineTicket.AirlineTicketAndFlightInfo;
import com.github.supercoding.repository.airlineTicket.AirlineTicketJpaRepository;
import com.github.supercoding.repository.airlineTicket.AirlineTicketRepository;
import com.github.supercoding.repository.passenger.Passenger;
import com.github.supercoding.repository.passenger.PassengerJpaRepository;
import com.github.supercoding.repository.passenger.PassengerRepository;
import com.github.supercoding.repository.reservations.Reservation;
import com.github.supercoding.repository.reservations.ReservationJpaRepository;
import com.github.supercoding.repository.reservations.ReservationRepository;
import com.github.supercoding.repository.users.UserEntity;
import com.github.supercoding.repository.users.UserJpaRepository;
import com.github.supercoding.repository.users.UserRepository;
import com.github.supercoding.service.exceptions.InvalidValueException;
import com.github.supercoding.service.exceptions.NotAcceptException;
import com.github.supercoding.service.exceptions.NotFoundException;
import com.github.supercoding.service.mapper.TicketMapper;
import com.github.supercoding.web.dto.airline.ReservationRequest;
import com.github.supercoding.web.dto.airline.ReservationResult;
import com.github.supercoding.web.dto.airline.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirReservationService {
    private final UserJpaRepository userJpaRepository;
    private final AirlineTicketJpaRepository airlineTicketJpaRepository;
    private final PassengerJpaRepository passengerJpaRepository;
    private final ReservationJpaRepository reservationJpaRepository;
    private final AirlineTicketRepository airlineTicketRepository;

    public List<Ticket> findUserFavoritePlaceTickets(Integer userId, String ticketType) {
        // 필요한 Repository : userRepository, airlineTicket Repository,
        // 1. 유저의 userId로 가져와서 ,선호하는 여행지 도출
        // 2. 선호하는 여행지와 ticketType으로 AirlineTicket table 질의해서 필요한 airlineTicket
        // 3. 이 둘의 정보를 조합해서 Ticket DTO를 만든다.

        Set<String> ticketTypeSet = new HashSet<>(Arrays.asList("편도","왕복"));
        if ( !ticketTypeSet.contains(ticketType) )
            throw new InvalidValueException("해당 TicketType: " + ticketType + " 은 지원하지 않습니다.");

        UserEntity userEntity = userJpaRepository.findById(userId).orElseThrow(() -> new NotFoundException("해당 ID: " + userId +" 유저를 찾을 수 없습니다."));

        String likePlace = userEntity.getLikeTravelPlace();
        List<AirlineTicket> airlineTickets = airlineTicketJpaRepository.findAirlineTicketsByArrivalLocationAndTicketType(likePlace, ticketType);

        if (airlineTickets.isEmpty())
            throw new NotFoundException("해당 likePlace: " + likePlace + " 와 TicketType: " + ticketType + "에 해당하는 항공권 찾을 수 없습니다.");

        List<Ticket> tickets = airlineTickets.stream().map(TicketMapper.INSTANCE::airlineTicketToTicket).collect(Collectors.toList());
        return tickets;
    }

    @Transactional(transactionManager = "tmJpa2")
    public ReservationResult makeReservation(ReservationRequest reservationRequest) {
        // Repository : Reservation Repository, Join Table( flight/airline_ticket ), Passenger Repository
        // 1. userId, airlineTicketId
        Integer userId = reservationRequest.getUserId();
        Integer airlineTicketId = reservationRequest.getAirlineTicketId();

        // 2. get Passenger ID
        Passenger passenger = passengerJpaRepository.findPassengerByUserId(userId)
                                                    .orElseThrow(()->new NotFoundException("요청하신 userid " + userId + "에 해당하는 Passenger를 찾을 수 없습니다."));

        Integer passengerId = passenger.getPassengerId();

        // 3. price 등의 정보 불려오기( join )
        List<AirlineTicketAndFlightInfo> airlineTicketAndFlightInfos = airlineTicketRepository.findAllAirLineTicketAndFlightInfo(airlineTicketId);
        if (airlineTicketAndFlightInfos.isEmpty())
            throw new NotFoundException("AirlineTicket Id " + airlineTicketId + " 에 해당하는 항공편과 항공권 찾을 수 없습니다.");

        // 4. Reservation 생성
        Boolean isSuccess = false;
        Reservation reservation = new Reservation(passengerId, airlineTicketId);
        try {
            reservationJpaRepository.save(reservation);
            isSuccess = true;
        } catch (RuntimeException e){
            throw new NotAcceptException("Reservation이 등록되는 과정이 거부되었습니다.");
        }
        // ReservationResult DTO 만들기
        List<Integer> prices = airlineTicketAndFlightInfos.stream().map(AirlineTicketAndFlightInfo::getPrice).collect(Collectors.toList());
        List<Integer> charges = airlineTicketAndFlightInfos.stream().map(AirlineTicketAndFlightInfo::getCharge).collect(Collectors.toList());
        Integer tax = airlineTicketAndFlightInfos.stream().map(AirlineTicketAndFlightInfo::getTax).findFirst().get();
        Integer totalPrice = airlineTicketAndFlightInfos.stream().map(AirlineTicketAndFlightInfo::getTotalPrice).findFirst().get();

        return new ReservationResult(prices, charges, tax, totalPrice, isSuccess);
    }
}
