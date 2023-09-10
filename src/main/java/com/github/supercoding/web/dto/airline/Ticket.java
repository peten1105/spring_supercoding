package com.github.supercoding.web.dto.airline;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.supercoding.respository.airlineTicket.AirlineTicket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @ApiModelProperty(name = "depart", value = "승객 출발지", example = "서울") private String depart;
    @ApiModelProperty(name = "arrival", value = "승객 도착지", example = "파리") private String arrival;
    @ApiModelProperty(name = "departureTime", value = "항공권 출발시간", example = "2023-05-05 11:00:00") private String departureTime;
    @ApiModelProperty(name = "returnTime", value = "항공권 귀국시간", example ="2023-05-07 11:00:00") private String returnTime;
    @ApiModelProperty(name = "TicketId", value = "Ticket ID", example = "1") private Integer ticketId;
}
