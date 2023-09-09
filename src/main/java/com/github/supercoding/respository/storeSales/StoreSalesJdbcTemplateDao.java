package com.github.supercoding.respository.storeSales;

import org.apache.catalina.Store;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StoreSalesJdbcTemplateDao implements StoreSalesRepository {

    private JdbcTemplate jdbcTemplate;
    static RowMapper<StoreSales> storeSalesRowMapper = (((rs, rowNum) ->
            new StoreSales(
                    rs.getInt("id"),
                    rs.getNString("store_name"),
                    rs.getInt("amount")
            )
    ));
    public StoreSalesJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public StoreSales findSotreSalesById(Integer storeId) {
        return jdbcTemplate.queryForObject("SELECT * from store_sales WHERE id = ?;", storeSalesRowMapper, storeId);
    }

    @Override
    public void updateSalesAmount(Integer storeId, Integer amount) {
        jdbcTemplate.update("UPDATE store_sales " +
                " SET amount = ? " +
                " WHERE id = ? ", amount, storeId);
    }
}
