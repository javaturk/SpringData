package org.javaturk.spring.data.ch03.spring.localEMFBean.dao;

import java.util.List;

import org.javaturk.spring.data.ch03.jpa.spring.domain.Person;
import org.javaturk.spring.data.ch03.jpa.spring.domain.ex.NoSuchPersonException;


public interface PersonDao {
	
	public void savePerson(Person person);
	
	public int retrievePersonCount();
	
	public Person retrievePerson(int id) throws NoSuchPersonException;
	
	public List<Person> retrieveAllPersons();
	
	public void updatePersonDOB(Person person);
	
	public void deletePerson(Person person);
	
	public void deleteAllPersons();

}
