package org.javaturk.spring.data.ch04.repository.jdbc.basic;

import java.util.Date;
import java.util.List;

import org.javaturk.spring.data.ch02.jdbc.basic.dao.PersonJdbcDAO;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.PersonFactory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.ex.NoSuchPersonException;

public class JdbcTest {
	private static PersonJdbcDAO dao = new PersonJdbcDAO();
	
	public static void main(String[] args) throws NoSuchPersonException {
		System.out.println("Number of person: " + dao.retrievePersonCount());
		
//		int id = 2;
//		Person person2 = new Person(id, "Ali", "Ozmen", new Date());
//		dao.savePerson(person2);
//		
//		Person personRetrieved = null;
//		try {
//			personRetrieved = dao.retrievePerson(id);
//			System.out.println(personRetrieved);
//		} catch (NoSuchPersonException e) {
//			System.out.println("Person does not exists!");
//			System.out.println(e.getMessage());
//		}
		
//		dao.deletePerson(personRetrieved);
		
//		savePersons(100);
		
//		List<Person> persons = dao.retrieveAllPersons();
//		for(Person person : persons)
//			System.out.println(person);
		
//		int id = 2;
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
	public static void savePersons(int n) {
		System.out.println("\nSaving " + n + " persons to DB");
		PersonFactory personFactory = PersonFactory.getInstance();
		for (int i = 0; i < n; i++) {
			Person p = personFactory.createPerson();
			dao.savePerson(p);
		}
	}
}
