package org.javaturk.spring.data.ch02.spring.namedParameterJdbcTemplate.person.callback;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class PersonSqlParameterSource implements SqlParameterSource {
	private Person person;

	public PersonSqlParameterSource(Person person) {
		this.person = person;
	}

	@Override
	public Object getValue(String paramName) throws IllegalArgumentException {
		Object value = switch (paramName) {
		case "id" -> person.getId();
		case "firstName" -> person.getFirstName();
		case "lastName" -> person.getLastName();
		case "dob" -> person.getDobAsSQlDate();
		default -> null;
		};
		return value;
	}

	@Override
	public boolean hasValue(String paramName) {
		return true;
	}
}
