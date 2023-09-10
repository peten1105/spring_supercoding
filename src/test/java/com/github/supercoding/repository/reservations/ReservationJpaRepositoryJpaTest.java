package com.github.supercoding.repository.reservations;

import com.github.supercoding.repository.airlineTicket.AirlineTicket;
import com.github.supercoding.repository.airlineTicket.AirlineTicketJpaRepository;
import com.github.supercoding.repository.passenger.Passenger;
import com.github.supercoding.repository.passenger.PassengerJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest // slice test => dao Layer / Jpa 사용하고 있는 slice test
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ReservationJpaRepositoryJpaTest {

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @Autowired
    private PassengerJpaRepository passengerJpaRepository;

    @Autowired
    private AirlineTicketJpaRepository airlineTicketJpaRepository;


    @DisplayName("ReservationRepository로 항공편 가격과 수수료 검색")
    @Test
    void findFlightPriceAndCharge() {
        // given
        Integer userId = 10;

        // when
        List<FlightPriceAndCharge> flightPriceAndChargeList = reservationJpaRepository.findFlightPriceAndCharge(userId);

        // then
        log.info("결과 : " + flightPriceAndChargeList);
    }

    @DisplayName("Reservation 예약 진행")
    @Test
    void saveReservation() {
        // given
        Integer userId = 10;
        Integer ticketId = 5;

        Passenger passenger = passengerJpaRepository.findPassengerByUserUserId(userId).get();
        AirlineTicket airlineTicket = airlineTicketJpaRepository.findById(ticketId).get();

        // when
        Reservation reservation = new Reservation(passenger, airlineTicket);
        Reservation res = reservationJpaRepository.save(reservation);

        // then
        log.info("결과 : " + res);

        assertEquals(res.getPassenger(), passenger);
        assertEquals(res.getAirlineTicket(), airlineTicket);
    }
}