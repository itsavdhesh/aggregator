package com.trade.aggregator.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Repository
public class StudentJdbcRepository {
	//@Autowired
	JdbcTemplate jdbcTemplate;

	public Student findById(long id) {
		return jdbcTemplate.queryForObject("select * from billionaires ",
				new BeanPropertyRowMapper<Student>(Student.class));
	}

	public List<Student> findAll() {
		return jdbcTemplate.query("select * from student", new StudentRowMapper());
	}
}
//@Component
class StudentRowMapper implements RowMapper<Student> {
//	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(rs.getLong("id"));
		student.setName(rs.getString("name"));
		student.setPassportNumber(rs.getString("passportNumber"));
		return student;
	}
}
