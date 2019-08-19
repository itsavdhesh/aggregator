package com.trade.aggregator.dblayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class StockJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Stock> findAll() {
		return jdbcTemplate.query("select * from STOCKS", new StockRowMapper());
	}
}

@Component
class StockRowMapper implements RowMapper<Stock> {
	@Override
	public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stock stock = new Stock();
		stock.setId(rs.getLong("id"));
		stock.setStock(rs.getString("stockCode"));
		stock.setLastPrice(rs.getDouble("lastPrice"));
		return stock;
	}
}