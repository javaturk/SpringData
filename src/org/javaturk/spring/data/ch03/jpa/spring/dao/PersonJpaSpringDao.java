package org.javaturk.spring.data.ch03.jpa.spring.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.javaturk.spring.data.ch03.jpa.spring.domain.*;
import org.javaturk.spring.data.ch03.jpa.spring.domain.ex.NoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonJpaSpringDao implements PersonDao {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void savePerson(Person person) {
		System.out.println("Saving person:" + person + "\n");
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(person);
		tx.commit();
		em.close();
		
		System.out.println("Person saved!");
	}

	@Override
	public Person retrievePerson(int id) throws NoSuchPersonException{
		System.out.println("Retrieving the person with id = " + id);
		EntityManager em = entityManagerFactory.createEntityManager();
		Person personRetrieved = em.find(Person.class, id);
		em.close();
		if (personRetrieved == null) {
			System.out.println("No such person with id = " + id);
			throw new NoSuchPersonException(id);
		} else {
			return personRetrieved;
		}
	}
	
	@Override
	public int retrievePersonCount() {
		System.out.println("\nRetrieving all persons by query.");
		EntityManager em = entityManagerFactory.createEntityManager();
		Query allPersons = em.createQuery("Select p from Person p");
		List<Person> persons = allPersons.getResultList();
		int size = persons.size();
		em.close();
		return size;
	}

	@Override
	public List<Person> retrieveAllPersons() {
		System.out.println("\nRetrieving all persons by query.");
		EntityManager em = entityManagerFactory.createEntityManager();
		Query allPersons = em.createQuery("Select p from Person p");
		List<Person> persons = allPersons.getResultList();
		System.out.println(persons.size() + " objects:");
		em.close();
		return persons;
	}

	@Override
	public void updatePersonDOB(Person person) {
		System.out.println("\nUpdating a person's date of birth:" + person);
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Person updatedPerson = em.merge(person);
		if(updatedPerson == null){
			System.out.println("No such person with id = " + person.getId());
			System.out.println("No person updated!");
		}
		else
			System.out.println("Person updated!");
		tx.commit();
		em.close();

	}

	@Override
	public void deletePerson(Person person) {
		System.out.println("\nDeleting a person:" + person);
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Person personToDelete = em.find(Person.class, person.getId());
		if(personToDelete == null){
			System.out.println("No such person with id = " + person.getId());
			System.out.println("No person deleted!");
		}
		else{
			em.remove(personToDelete);
			System.out.println("Person deleted!");
		}
		tx.commit();
		em.close();
	}

	@Override
	public void deleteAllPersons() {
		System.out.println("\nDeleting all persons.");
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query allPersons = em.createQuery("Select p from Person p");
		List<Person> persons = allPersons.getResultList();
		for(Person person:persons)
			em.remove(person);
		System.out.println(persons.size() + " persons deleted!");
		tx.commit();
		em.close();
	}
}
