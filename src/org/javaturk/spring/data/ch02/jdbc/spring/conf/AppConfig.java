package org.javaturk.spring.data.ch02.jdbc.spring.conf;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.PersonFactory;
import org.javaturk.spring.data.ch02.jdbc.spring.dao.PersonJdbcConnectionDAO;
import org.javaturk.spring.data.ch02.jdbc.spring.dao.PersonJdbcDataSourceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
	
	@Autowired
	private Factory personFactory;
	
	@Bean
	@Scope("prototype")
	public Person person() {
		return personFactory.createPerson();
	}
	
	@Bean
	@Scope("singleton")
	public PersonFactory personFactory() {
		return  PersonFactory.getInstance();
	}
	
	@Bean
	@Qualifier("personJdbcConnectionDAO")
	@Scope("prototype")
	public PersonJdbcConnectionDAO personJdbcConnectionDAO() {
		System.out.println("Creating personJdbcConnectionDAO");
		return new PersonJdbcConnectionDAO();
	}
	
	@Bean
	@Qualifier("personJdbcDataSourceDAO")
	@Scope("prototype")
	public PersonJdbcDataSourceDAO personJdbcDataSourceDAO() {
		System.out.println("Creating personJdbcDataSourceDAO");
		return new PersonJdbcDataSourceDAO();
	} 
}
