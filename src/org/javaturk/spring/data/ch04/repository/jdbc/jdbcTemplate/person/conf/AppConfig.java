package org.javaturk.spring.data.ch04.repository.jdbc.jdbcTemplate.person.conf;

import javax.sql.DataSource;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.PersonFactory;
import org.javaturk.spring.data.ch04.repository.jdbc.jdbcTemplate.person.JdbcTemplateRespositoryTest;
import org.javaturk.spring.data.ch04.repository.jdbc.jdbcTemplate.person.dao.PersonJdbcTemplateDAO;
//import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
	
//	@Bean
//	@Scope("prototype")
//	public PersonJdbcTemplateDAO personJdbcTemplateDAO() {
//		System.out.println("Creating PersonJdbcTemplateDAO");
//		return new PersonJdbcTemplateDAO();
//	} 
}
