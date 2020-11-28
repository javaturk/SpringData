package org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.dao;

import javax.annotation.PostConstruct;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.callback.PersonSqlParameterSource;
import org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.callback.PersonSqlParameterSourceWithoutDob;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class SimpleJdbcInsertDao2 implements PersonInsertDao{
	
	@Autowired
	private SimpleJdbcInsert simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcInsert simpleJdbcInsertWithoutDob;
	
	@Override
	public void savePerson(Person person) {
		System.out.println("\nSaving person:" + person);
		PersonSqlParameterSource psps = new PersonSqlParameterSource(person);
		int count = simpleJdbcInsert.execute(psps);
		if (count == 1)
			System.out.println("Person saved.");
		else
			System.out.println("Problem when saving Person.");
	}

	@Override
	public void savePersonWithoutDob(Person person) {
		System.out.println("\nSaving person without Dob:" + person);
		PersonSqlParameterSourceWithoutDob psps = new PersonSqlParameterSourceWithoutDob(person);
		int count = simpleJdbcInsertWithoutDob.execute(psps);
		if (count == 1)
			System.out.println("Person saved.");
		else
			System.out.println("Problem when saving Person.");		
	}
	
	@PostConstruct
	public void init() {
		simpleJdbcInsert.setTableName("PERSONS");
		simpleJdbcInsertWithoutDob.setTableName("PERSONS");
	}
}
