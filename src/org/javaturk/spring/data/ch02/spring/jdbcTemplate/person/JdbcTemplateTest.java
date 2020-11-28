package org.javaturk.spring.data.ch02.spring.jdbcTemplate.person;

import java.util.Date;
import java.util.List;

import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.conf.AppConfig;
import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.conf.JDBCConfig;
import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.dao.PersonJdbcTemplateDAO;
import org.javaturk.spring.data.ch02.jdbc.common.data.PersonDAO;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.ex.NoSuchPersonException;
import org.javaturk.spring.data.ch02.jdbc.spring.SpringTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@Import({ AppConfig.class, JDBCConfig.class })
public class JdbcTemplateTest {
	// This should be before personFactory otherwise "Is there an unresolvable circular reference?"
	@Autowired
	PersonDAO dao;
	
	@Autowired
	Factory personFactory;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplateTest.class);
		JdbcTemplateTest test = context.getBean(JdbcTemplateTest.class);
		test.run();
	}

	public void run() {
		System.out.println("Number of person: " + dao.retrievePersonCount());

		int id = 11;
//		Person person = new Person(id, "Ali", "Ozmen", new Date());
//		dao.savePerson(person);

		Person personRetrieved = null;
		try {
			personRetrieved = dao.retrievePerson(id);
			System.out.println(personRetrieved);
		} catch (NoSuchPersonException e) {
			System.out.println("Person does not exists!");
			System.out.println(e.getMessage());
		}
		
//		dao.deletePerson(personRetrieved);

//		savePersons(100);

//		List<Person> persons = dao.retrieveAllPersons();
//		System.out.println("There are " + persons.size() + " persons.\n");
//		for(Person person : persons)
//			System.out.println(person);

//		int id = 1;
//		Person person = dao.retrievePerson(id);
//		System.out.println("Before update: " + person);
//		person.setDob(new Date(2000000000));
//		dao.updatePersonDOB(person);
//		person = dao.retrievePerson(id);
//		System.out.println("After update: " + person);	

//		dao.deleteAllPersons();
	}

	/**
	 * Utility method to populate the database
	 * 
	 * @param n Number of the persons to be created.
	 */
	public void savePersons(int n) {
		System.out.println("\nSaving " + n + " persons to DB");
		for (int i = 0; i < n; i++) {
			Person p = personFactory.createPerson();
			dao.savePerson(p);
		}
	}
}
