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
public class ClientJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Client> findAll() {
		return jdbcTemplate.query("select * from Clients", new ClientRowMapper());
	}
}

@Component
class ClientRowMapper implements RowMapper<Client> {
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Client client = new Client();
		client.setId(rs.getLong("id"));
		client.setClientCode(rs.getString("clientCode"));
		client.setClientName(rs.getString("clientName"));
		return client;
	}
}