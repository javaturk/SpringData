package org.javaturk.spring.data.ch03.spring.localEMFBean.conf;

import org.javaturk.spring.data.ch03.jpa.spring.domain.Factory;
import org.javaturk.spring.data.ch03.jpa.spring.domain.PersonFactory;
import org.javaturk.spring.data.ch03.spring.localEMFBean.dao.PersonDao;
import org.javaturk.spring.data.ch03.spring.localEMFBean.dao.PersonJpaSpringLEMFBDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
	
	@Bean
	@Scope("prototype")
	public PersonDao PersonJpaSpringDao() {
		System.out.println("Creating PersonJpaSpringLEMFBDao");
		return new PersonJpaSpringLEMFBDao(); 
	}
	
	@Bean
	@Scope("prototype")
	public Factory personFactory() {
		System.out.println("Creating PersonFactory");
		return PersonFactory.getInstance(); 
	}
}
