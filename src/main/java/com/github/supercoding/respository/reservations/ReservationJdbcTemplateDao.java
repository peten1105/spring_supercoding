package com.github.supercoding.respository.reservations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;

@Repository
public class ReservationJdbcTemplateDao implements ReservationRepository{

    private JdbcTemplate template;

    public ReservationJdbcTemplateDao(@Qualifier("jdbcTemplate2") JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public boolean saveReservation(Reservation reservation) {
        Integer rowNums = template.update("INSERT INTO reservation(passenger_id, airline_ticket_id, reservation_status, reserve_at) VALUES (? ,? , ?, ? )",
                reservation.getPassengerId(), reservation.getAirlineTicketId(), reservation.getReservationStatus(),
                new Date(Timestamp.valueOf(reservation.getReserveAt()).getTime()));
        return rowNums > 0;
    }
}
