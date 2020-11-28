package org.javaturk.spring.data.ch02.spring.namedParameterJdbcTemplate.person.conf;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.PersonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("org/javaturk/spring/data/ch02/spring/namedParameterJdbcTemplate/person/resources/jdbc.properties")
public class JDBCConfig{
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${jdbc.driverClassName}")
	private String driver;
	
	@Autowired
	private Environment env;
	
	@Bean
	@Scope("prototype")
	public DataSource dataSource1() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		basicDataSource.setUrl(env.getProperty("jdbc.url"));
		basicDataSource.setUsername(env.getProperty("jdbc.username"));
		basicDataSource.setPassword(env.getProperty("jdbc.password"));
		return basicDataSource;
	}
	
	@Bean
	@Scope("prototype")
	public DataSource dataSource2() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}
	
	@PostConstruct
	public void listProperties() {
		System.err.format("\n******************************************************************************\n");
		System.err.format("* %50s %25s \n", "Creating the connection with following properties", "*");
		System.err.format("* %-20s %-50s %4s \n", "URL:", url, "*");
		System.err.format("* %-20s %-50s %4s \n", "Username:", username, "*");
		System.err.format("* %-20s %-50s %4s \n", "Password:", password, "*");
		System.err.format("* %-20s %-50s %4s \n", "Driver:", driver, "*");
		System.err.format("******************************************************************************\n");
		System.out.println();
	}
}
