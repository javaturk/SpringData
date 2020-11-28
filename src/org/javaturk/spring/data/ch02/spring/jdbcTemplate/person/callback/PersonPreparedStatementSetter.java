package org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class PersonPreparedStatementSetter implements PreparedStatementSetter{
	private Person person;

	public PersonPreparedStatementSetter(Person person) {
		this.person = person;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setInt(1,  person.getId());
		ps.setString(2,  person.getFirstName());
		ps.setString(3,  person.getLastName());
		ps.setDate(4,  person.getDobAsSQlDate());
	}
}
