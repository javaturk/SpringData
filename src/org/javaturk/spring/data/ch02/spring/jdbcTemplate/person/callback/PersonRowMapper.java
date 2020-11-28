package org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.callback;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.jdbc.core.RowMapper;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int arg1) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("ID"));
		person.setFirstName(rs.getString("FIRSTNAME"));
		person.setLastName(rs.getString("LASTNAME"));
		person.setDob(rs.getDate("DATEOFBIRTH"));
		return person;
	}
}
