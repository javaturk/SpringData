package org.javaturk.spring.data.ch02.spring.namedParameterJdbcTemplate.person.conf;

import javax.sql.DataSource;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.PersonFactory;
import org.javaturk.spring.data.ch02.spring.namedParameterJdbcTemplate.person.dao.PersonNamedParameterJdbcTemplateDAO;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
	public PersonNamedParameterJdbcTemplateDAO personNamedParameterJdbcTemplateDAO() {
		System.out.println("Creating personNamedParameterJdbcTemplateDAO");
		return new PersonNamedParameterJdbcTemplateDAO();
	} 

	@Bean
	@Scope("prototype")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource1);
	} 
}
