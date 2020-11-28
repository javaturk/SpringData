package org.javaturk.spring.data.ch03.spring.localEMFBean;

import java.util.Date;
import java.util.List;

import org.javaturk.spring.data.ch03.spring.localEMFBean.dao.*;
import org.javaturk.spring.data.ch03.jpa.spring.domain.Factory;
import org.javaturk.spring.data.ch03.jpa.spring.domain.Person;
import org.javaturk.spring.data.ch03.jpa.spring.domain.ex.NoSuchPersonException;
import org.javaturk.spring.data.ch03.spring.localEMFBean.conf.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@Import({ AppConfig.class, JPAConfig.class })
public class LocalEntityManagerFactoryBeanTest {
	private static AnnotationConfigApplicationContext context;
	private PersonDao dao;

	public static void main(String[] args) throws NoSuchPersonException {
		context = new AnnotationConfigApplicationContext(LocalEntityManagerFactoryBeanTest.class);
		LocalEntityManagerFactoryBeanTest test = context.getBean(LocalEntityManagerFactoryBeanTest.class);
		test.dao = context.getBean(PersonDao.class);
		test.run();
	}
	
	public void run() throws NoSuchPersonException {
		System.out.println("Number of person: " + dao.retrievePersonCount());

		int id = 7;
		Person person2 = new Person(id, "Ali", "Ozmen", new Date());
		dao.savePerson(person2);

		Person personRetrieved = null;
		try {
			personRetrieved = dao.retrievePerson(id);
			System.out.println(personRetrieved);
		} catch (NoSuchPersonException e) {
			System.out.println("Person does not exists!");
			System.out.println(e.getMessage());
		}

		dao.deletePerson(personRetrieved);

		savePersons(100);

		List<Person> persons = dao.retrieveAllPersons();
		for(Person person : persons)
			System.out.println(person);

//		int id = 1356865398;
//		Person person = dao.retrievePerson(id);
//		System.out.println("Before update: " + person);
//		person.setDob(new Date(2000000000));
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
		Factory personFactory = context.getBean(Factory.class);
		for (int i = 0; i < n; i++) {
			Person p = personFactory.createPerson();
			dao.savePerson(p);
		}
	}
}
