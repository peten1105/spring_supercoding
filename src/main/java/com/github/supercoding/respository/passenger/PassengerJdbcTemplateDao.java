package com.github.supercoding.respository.passenger;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerJdbcTemplateDao implements PassengerRepository {
    private JdbcTemplate template;

    public PassengerJdbcTemplateDao(@Qualifier("jdbcTemplate2") JdbcTemplate template) {
        this.template = template;
    }

    static RowMapper<Passenger> passengerRowMapper = (((rs, rowNum) ->
            new Passenger(
                    rs.getInt("passenger_id"),
                    rs.getInt("user_id"),
                    rs.getNString("passport_num"))
    ));
    @Override
    public Passenger findPassengerByUserId(Integer userId) {
        return template.queryForObject("SELECT * FROM passenger WHERE user_id = ?", passengerRowMapper, userId);
    }
}
