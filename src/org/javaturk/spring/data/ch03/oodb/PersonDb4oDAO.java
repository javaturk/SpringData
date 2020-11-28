package org.javaturk.spring.data.ch03.oodb;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.javaturk.spring.data.ch03.common.dao.PersonDao;
import org.javaturk.spring.data.ch03.common.domain.Person;
import org.javaturk.spring.data.ch03.common.domain.PersonFactory;
import org.javaturk.spring.data.ch03.common.domain.ex.NoSuchPersonException;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class PersonDb4oDAO implements PersonDao {
	
	final static String DB4OFILENAME = System.getProperty("user.home") + "/ch01.db4o";
	private static ObjectContainer db;
	private static PersonFactory factory;

	public PersonDb4oDAO() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
		factory = PersonFactory.getInstance();
	}

	@Override
	public void savePerson(Person person) {
		System.out.println("Saving person:" + person);
		db.store(person);
		System.out.println("Person saved!");
	}

	@Override
	public int retrievePersonCount() {
		ObjectSet<Person> persons = db.query(Person.class);
		int size = persons.size();
		return size;
	}
	
	@Override
	public Person retrievePerson(int id) throws NoSuchPersonException{
		System.out.println("Retrieving the person with id = " + id);
		Person prototype = new Person(id);
		ObjectSet<Person> result = db.queryByExample(prototype);
		int personCount = result.size();
		if(personCount > 0){
			Person personRetrieved = result.get(0); 
			return personRetrieved;
		}
		else
			throw new NoSuchPersonException(id);
	}

	@Override
	public List<Person> retrieveAllPersons() {
		System.out.println("\nRetrieving all persons by query.");
		ObjectSet<Person> persons = db.query(Person.class);
		System.out.println(persons.size() + " persons retrieved.");
		return persons;
	}

	@Override
	public void updatePersonDOB(Person person) {
		System.out.println("Updating a person's date of birth:" + person);
		Person prototype = new Person(person.getId());
		ObjectSet<Person> persons = db.queryByExample(prototype);
		
		int updateCount = persons.size();
		if(updateCount == 0){
			System.out.println("No such person with id = " + person.getId());
			System.out.println("No person updated!");
		}
		else{
			Person personToUpdate = persons.get(0);
			personToUpdate.setDob(person.getDob());
			db.store(personToUpdate);
			System.out.println("Person updated!");
		}
	}

	@Override
	public void deletePerson(Person person) {
		System.out.println("Deleting a person:" + person);
		Person prototype = new Person(person.getId(), person.getFirstName(), person.getLastName());
		ObjectSet<Person> persons = db.queryByExample(prototype);
		int updateCount = persons.size();
		if(updateCount == 0){
			System.out.println("No such person with id = " + person.getId());
			System.out.println("No person deleted!");
		}
		else{
			Person personToDelete = persons.get(0);
			db.delete(personToDelete);
			System.out.println("Person deleted!");
		}
	}

	@Override
	public void deleteAllPersons() {
		System.out.println("Deleting all persons.");
		ObjectSet<Person> persons = db.query(Person.class);
		int updateCount = persons.size();
		Iterator<Person> iterator = persons.iterator();
		while(iterator.hasNext()){
			Person person = iterator.next();
			db.delete(person);
		}
		System.out.println(updateCount + " persons deleted!");
	}
	
	public void closeDB(){
		db.close();
	}
}
