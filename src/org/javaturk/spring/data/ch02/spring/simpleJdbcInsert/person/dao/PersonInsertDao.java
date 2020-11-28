package org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.dao;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;

public interface PersonInsertDao {
	
	public void savePerson(Person person);

	public void savePersonWithoutDob(Person person);
}
