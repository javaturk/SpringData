package org.javaturk.spring.data.ch03.common.dao;

import java.util.List;

import org.javaturk.spring.data.ch03.common.domain.Person;
import org.javaturk.spring.data.ch03.common.domain.ex.NoSuchPersonException;

public interface PersonDao {
	
	public void savePerson(Person person);
	
	public int retrievePersonCount();
	
	public Person retrievePerson(int id) throws NoSuchPersonException;
	
	public List<Person> retrieveAllPersons();
	
	public void updatePersonDOB(Person person);
	
	public void deletePerson(Person person);
	
	public void deleteAllPersons();

}
