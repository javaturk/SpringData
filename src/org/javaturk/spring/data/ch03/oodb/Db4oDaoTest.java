package org.javaturk.spring.data.ch03.oodb;

import java.util.Date;
import java.util.List;

import org.javaturk.spring.data.ch03.common.domain.*;
import org.javaturk.spring.data.ch03.common.domain.Person;
import org.javaturk.spring.data.ch03.common.domain.ex.NoSuchPersonException;

public class Db4oDaoTest {
	private PersonDb4oDAO dao = new PersonDb4oDAO();
	private Factory personFactory;

	public static void main(String[] args) throws NoSuchPersonException {
		Db4oDaoTest test = new Db4oDaoTest();
		test.run();
	}

	public void run() throws NoSuchPersonException {
//		int id = 1;
//		Person person2 = new Person(id, "Ali", "Ozmen", new Date());
//		dao.savePerson(person2);
		
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

//		int id = 83920280;
//		Person person = dao.retrievePerson(id);
//		System.out.println("Before update: " + person);
//		person.setDob(new Date(2_000_000_000));
//		dao.updatePersonDOB(person);
//		person = dao.retrievePerson(id);
//		System.out.println("After update: " + person);

//		dao.deleteAllPersons();
	}

	/*
	 * Utility method to populate the database
	 * 
	 * @param n Number of the persons to be created.
	 */
	public void savePersons(int n) {
		System.out.println("\nSaving " + n + " persons to DB");
		PersonFactory personFactory = PersonFactory.getInstance();
		for (int i = 0; i < n; i++) {
			Person p = personFactory.createPerson();
			dao.savePerson(p);
		}
	}
}
