package org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person;

import java.util.Date;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.ex.NoSuchPersonException;
import org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.conf.*;
import org.javaturk.spring.data.ch02.spring.simpleJdbcInsert.person.dao.PersonInsertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@Import({ AppConfig.class, JDBCConfig.class })
public class SimpleJdbcInsertTest {

	@Autowired
	PersonInsertDao dao1;
	
	@Autowired
	PersonInsertDao dao2;

	@Autowired
	Factory personFactory;

	public static void main(String[] args) throws NoSuchPersonException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SimpleJdbcInsertTest.class);
		SimpleJdbcInsertTest test = context.getBean(SimpleJdbcInsertTest.class);
		test.run();
	}

	public void run() throws NoSuchPersonException {
		int id = 160;
		Person person = new Person(id, "Ali", "Ozmen", new Date());
		dao2.savePerson(person);

		id = 161;
		person = new Person(id, "Sami", "Tansu");
		dao2.savePersonWithoutDob(person);
		
//		savePersons(100);
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
			dao1.savePerson(p);
		}
	}
}
