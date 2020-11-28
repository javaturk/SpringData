package org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.callback;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class PersonSqlParameterSourceWithoutDob implements SqlParameterSource {
	private Person person;

	public PersonSqlParameterSourceWithoutDob(Person person) {
		this.person = person;
	}

	@Override
	public Object getValue(String paramName) throws IllegalArgumentException {
		Object value = switch (paramName) {
		case "ID" -> person.getId();
		case "FIRSTNAME" -> person.getFirstName();
		case "LASTNAME" -> person.getLastName();
		default -> null;
		};
		return value;
	}

	@Override
	public boolean hasValue(String arg0) {
		return true;
	}
}
