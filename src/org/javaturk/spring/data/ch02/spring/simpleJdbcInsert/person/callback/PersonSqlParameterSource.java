package org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.callback;

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
		case "ID" -> person.getId();
		case "FIRSTNAME" -> person.getFirstName();
		case "LASTNAME" -> person.getLastName();
		case "DATEOFBIRTH" -> person.getDobAsSQlDate();
		default -> null;
		};
		return value;
	}

	@Override
	public boolean hasValue(String paramName) {
		return true;
	}
}
