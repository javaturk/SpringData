package org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.conf;

import javax.sql.DataSource;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.PersonFactory;
import org.javaturk.spring.data.ch02.spring.namedParameterJdbcTemplate.person.dao.PersonNamedParameterJdbcTemplateDAO;
import org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.dao.*;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
public class AppConfig {
	
	@Autowired
	private Factory personFactory;
	
	@Autowired
	private DataSource dataSource1;
	
	@Bean
	@Scope("prototype")
	public Person person() {
		return personFactory.createPerson();
	}
	
	@Bean
	@Scope("singleton")
	public PersonFactory personFactory() {
		return PersonFactory.getInstance();
	}
	
	@Bean
	@Scope("prototype")
	public SimpleJdbcInsertDao1 dao1() {
		System.out.println("Creating simpleJdbcInsertDao1");
		return new SimpleJdbcInsertDao1();
	} 
	
	@Bean
	@Scope("prototype")
	public SimpleJdbcInsertDao2 dao2() {
		System.out.println("Creating simpleJdbcInsertDao2");
		return new SimpleJdbcInsertDao2();
	} 

	@Bean
	@Scope("prototype")
	public SimpleJdbcInsert simpleJdbcInsert() {
		return new SimpleJdbcInsert(dataSource1);
	} 
}
