package org.javaturk.spring.data.ch03.spring.localContainerEMFBean.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.PersonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.core.env.*;

@Configuration
//@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.AUTODETECT)
@PropertySource("org/javaturk/spring/data/ch03/spring/localContainerEMFBean/resources/jpa.properties")
public class JPAConfig {

	@Value("${unitName}")
	private String unitName;

	@Value("${javax.persistence.provider}")
	private String provider;
	
	@Value("${eclipselink.weaving}")
	private String eclipselinkWeaving;

	@Value("${javax.persistence.jdbc.url}")
	private String url;

	@Value("${javax.persistence.jdbc.user}")
	private String user;

	@Value("${javax.persistence.jdbc.password}")
	private String password;

	@Value("${javax.persistence.jdbc.driver}")
	private String driver;

	@Value("${javax.persistence.schema-generation.database.action}")
	private String action;

	private Map<String, Object> properties;

	@Autowired
	private Environment env;

	@Bean
	public Map<String, Object> properties() {
		properties = new HashMap<>();
		properties.put("javax.persistence.provider", provider);
		properties.put("javax.persistence.jdbc.url", url);
		properties.put("javax.persistence.jdbc.driver", driver);
		properties.put("javax.persistence.jdbc.user", user);
		properties.put("javax.persistence.jdbc.password", password);
//		properties.forEach((k, v) -> System.out.println(k + " " + v));
		return properties;
	}

	@Bean
	@Scope("prototype")
	public DataSource dataSource1() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getProperty("javax.persistence.jdbc.driver"));
		basicDataSource.setUrl(env.getProperty("javax.persistence.jdbc.url"));
		basicDataSource.setUsername(env.getProperty("javax.persistence.jdbc.user"));
		basicDataSource.setPassword(env.getProperty("javax.persistence.jdbc.password"));
		return basicDataSource;
	}

	@Bean
	@Scope("prototype")
	public DataSource dataSource2() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(user);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
		System.out.println("Creating LocalContainerEntityManagerFactoryBean");
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceUnitName(unitName);
		em.setJpaPropertyMap(properties);
		em.setDataSource(dataSource2());

		JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String,Object> jpaProperties = new HashMap<>();
		jpaProperties.put(PersistenceUnitProperties.WEAVING, "false");
		em.setJpaPropertyMap(jpaProperties);
		return em;
	}

	@PostConstruct
	public void listProperties() {
		System.out.format("******************************************************************************\n");
		System.out.format("* %50s %20s \n", "Creating the persistence unit with following properties", "*");
		System.out.format("* %-20s %-50s %4s \n", "Unit name:", unitName, "*");
		System.out.format("* %-20s %-50s %4s \n", "Provider:", provider, "*");
		System.out.format("* %-20s %-50s %4s \n", "URL:", url, "*");
		System.out.format("* %-20s %-50s %4s \n", "Username:", user, "*");
		System.out.format("* %-20s %-50s %4s \n", "Password:", password, "*");
		System.out.format("* %-20s %-50s %4s \n", "Driver:", driver, "*");
		System.out.format("* %-20s %-50s %4s \n", "Database action:", action, "*");
//		System.out.format("* %-70s %5s \n", "       *  *  *   Other   Properties   *  *  *  ", "*");

//		Set<String> keys = properties.keySet();
//		for(Object key : keys) {
//			String stringKey = (String) key;
//			if(!stringKey.startsWith("javax"))
//				System.out.format("* %-50s %-20s %4s \n", stringKey + ":", properties.get(stringKey), "*");
//		}
		System.out.format("******************************************************************************\n");
	}
}
