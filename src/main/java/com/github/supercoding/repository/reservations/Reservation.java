package com.github.supercoding.repository.reservations;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="reservationId")
@Builder
@Entity
@Table(name ="reservation")
public class Reservation {
    @Id @Column(name = "reservation_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(name = "passenger_id")
    private Integer passengerId;
    @Column(name = "airline_ticket_id")
    private Integer airlineTicketId;

    @Column(name = "reservation_status", length = 10)
    private String reservationStatus;

    @Column(name = "reserve_at")
    private LocalDateTime reserveAt;

    public Reservation(Integer passengerId, Integer airlineTicketId) {
        this.passengerId = passengerId;
        this.airlineTicketId = airlineTicketId;
        this.reservationStatus = "대기";
        this.reserveAt = LocalDateTime.now();
    }
}
