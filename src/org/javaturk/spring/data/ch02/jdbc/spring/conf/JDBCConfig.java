package org.javaturk.spring.data.ch02.jdbc.spring.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("org/javaturk/spring/data/ch02/jdbc/common/resources/jdbc.properties")
public class JDBCConfig {

	@Value("${url}")
	private String url;

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	@Value("${driver}")
	private String driver;

	private BasicDataSource basicDataSource;

	@Bean
	public DataSource dataSource() {
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}
	
	@Bean
	@Scope("prototype")
	public Connection connection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println(driver + " is not found: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("In JDBCConfig, problem with getting a connection: " + e.getMessage());
			e.printStackTrace();
		}
		return connection;
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
