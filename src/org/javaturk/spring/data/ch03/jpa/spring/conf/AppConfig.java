package org.javaturk.spring.data.ch03.jpa.spring.conf;

import org.javaturk.spring.data.ch03.jpa.spring.dao.*;
import org.javaturk.spring.data.ch03.jpa.spring.domain.Factory;
import org.javaturk.spring.data.ch03.jpa.spring.domain.PersonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
	
	@Bean
	@Scope("prototype")
	public PersonJpaSpringDao PersonJpaSpringDao() {
		System.out.println("Creating PersonJpaSpringDao");
		return new PersonJpaSpringDao(); 
	}
	
	@Bean
	@Scope("prototype")
	public Factory personFactory() {
		System.out.println("Creating PersonFactory");
		return PersonFactory.getInstance(); 
	}
}
