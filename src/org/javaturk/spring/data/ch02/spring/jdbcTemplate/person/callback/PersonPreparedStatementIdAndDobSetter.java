package org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class PersonPreparedStatementIdAndDobSetter implements PreparedStatementSetter{
	private Person person;

	public PersonPreparedStatementIdAndDobSetter(Person person) {
		this.person = person;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setDate(1,  person.getDobAsSQlDate());
		ps.setInt(2,  person.getId());
	}
}
