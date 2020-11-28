package org.javaturk.spring.data.ch02.jdbc.common.data;

import java.util.List;

import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.ex.NoSuchPersonException;

public interface PersonDAO {
	
	public void savePerson(Person person);
	
	public int retrievePersonCount();
	
	public Person retrievePerson(int id) throws NoSuchPersonException;
	
	public List<Person> retrieveAllPersons();
	
	public void updatePersonDOB(Person person);
	
	public void deletePerson(Person person);
	
	public void deleteAllPersons();

}
