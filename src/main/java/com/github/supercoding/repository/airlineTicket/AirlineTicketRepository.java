package com.github.supercoding.repository.airlineTicket;

import java.util.List;

public interface AirlineTicketRepository {
    List<AirlineTicket> findAllAirlineTicketsWithPlaceAndTicketType(String likePlace, String ticketType);

    List<AirlineTicketAndFlightInfo> findAllAirLineTicketAndFlightInfo(Integer airlineTicketId);
}
