package org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.dao;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.callback.PersonSqlParameterSource;
import org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.callback.PersonSqlParameterSourceWithoutDob;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class SimpleJdbcInsertDao1 implements PersonInsertDao, BeanFactoryAware{
	
	@Autowired
	private SimpleJdbcInsert simpleJdbcInsert;
	
	private BeanFactory beanFactory;

	@Override
	public void savePerson(Person person) {
		System.out.println("\nSaving person:" + person);
		refreshSimpleJdbcInsert();
		PersonSqlParameterSource psps = new PersonSqlParameterSource(person);
		simpleJdbcInsert.setTableName("PERSONS");
		int count = simpleJdbcInsert.execute(psps);
		if (count == 1)
			System.out.println("Person saved.");
		else
			System.out.println("Problem when saving Person.");
	}

	@Override
	public void savePersonWithoutDob(Person person) {
		System.out.println("\nSaving person without Dob:" + person);
		refreshSimpleJdbcInsert();
		PersonSqlParameterSourceWithoutDob psps = new PersonSqlParameterSourceWithoutDob(person);
		simpleJdbcInsert.setTableName("PERSONS");
		int count = simpleJdbcInsert.execute(psps);
		if (count == 1)
			System.out.println("Person saved.");
		else
			System.out.println("Problem when saving Person.");		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	private void refreshSimpleJdbcInsert() {
		simpleJdbcInsert = beanFactory.getBean(SimpleJdbcInsert.class);
	}
}
