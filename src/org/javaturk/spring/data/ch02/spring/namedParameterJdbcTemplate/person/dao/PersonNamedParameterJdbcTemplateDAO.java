package org.javaturk.spring.data.ch02.spring.namedParameterJdbcTemplate.person.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javaturk.spring.data.ch02.jdbc.common.data.PersonDAO;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.spring.namedParameterJdbcTemplate.person.callback.*;
import org.javaturk.spring.data.ch02.jdbc.common.domain.ex.NoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class PersonNamedParameterJdbcTemplateDAO implements PersonDAO {
	private static final String SAVE_PERSON_QUERY = "INSERT INTO PERSONS VALUES(:id, :firstName, :lastName, :dob)";
	private static final String COUNT_QUERY = "SELECT COUNT(*) FROM PERSONS";
	private static final String RETRIEVE_PERSON_QUERY = "SELECT * FROM PERSONS WHERE ID = :id";
	private static final String RETRIEVE_ALL_PERSONS_QUERY = "SELECT * FROM PERSONS";
	private static final String UPDATE_PERSONDOB_QUERY = "UPDATE PERSONS SET DATEOFBIRTH = :dob WHERE ID = :id";
	private static final String DELETE_PERSON_QUERY = "DELETE FROM PERSONS WHERE ID = :id";
	private static final String DELETE_ALL_PERSONS_QUERY = "DELETE FROM PERSONS";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	Factory personFactory;

//	@Override
//	public void savePerson(Person person) {
//		System.out.println("\nSaving person:" + person);
//		Map<String, Object> namedParameterMap = new HashMap<>();
//		namedParameterMap.put("id", person.getId());
//		namedParameterMap.put("firstName", person.getFirstName());
//		namedParameterMap.put("lastName", person.getLastName());
//		namedParameterMap.put("dob", person.getDob());
//		int count = namedParameterJdbcTemplate.update(SAVE_PERSON_QUERY, namedParameterMap);
//		if (count == 1)
//			System.out.println("Person saved.");
//		else
//			System.out.println("Problem when saving Person.");
//	}
	
	@Override
	public void savePerson(Person person) {
		System.out.println("\nSaving person:" + person);
		PersonSqlParameterSource psps = new PersonSqlParameterSource(person);
		int count = namedParameterJdbcTemplate.update(SAVE_PERSON_QUERY, psps);
		if (count == 1)
			System.out.println("Person saved.");
		else
			System.out.println("Problem when saving Person.");
	}
	
	public void savePersons(int n) {
		System.out.println("\nSaving " + n + " persons to DB");
		for (int i = 0; i < n; i++) {
			Person p = personFactory.createPerson();
			savePerson(p);
		}
	}

	@Override
	public int retrievePersonCount() {
		return(int) namedParameterJdbcTemplate.queryForObject(COUNT_QUERY, new HashMap(), Integer.class);
	}

	@Override
	public Person retrievePerson(int id) throws NoSuchPersonException {
		Map<String, Integer> namedParameterMap = Collections.singletonMap("id", id);
		Person person = namedParameterJdbcTemplate.queryForObject(RETRIEVE_PERSON_QUERY, namedParameterMap, new PersonRowMapper());
		if (person.getId() == 0)
			throw new NoSuchPersonException(person.getId());
		return person;
	}

	@Override
	public List<Person> retrieveAllPersons() {
		return (List<Person>) namedParameterJdbcTemplate.query(RETRIEVE_ALL_PERSONS_QUERY, new PersonRowMapper());
	}

	@Override
	public void updatePersonDOB(Person person) {
		Map<String, Object> namedParameterMap = new HashMap<>();
		namedParameterMap.put("id", person.getId());
		namedParameterMap.put("dob", person.getDob());
		int count = namedParameterJdbcTemplate.update(UPDATE_PERSONDOB_QUERY, namedParameterMap);
		if (count == 1)
			System.out.println("Person has been updated.");
		else
			System.out.println("Problem when updating Person.");
	}

	@Override
	public void deletePerson(Person person) {
		Map<String, Integer> namedParameterMap = Collections.singletonMap("id", person.getId());
		namedParameterJdbcTemplate.update(DELETE_PERSON_QUERY, namedParameterMap);
	}

	@Override
	public void deleteAllPersons() {
		int count = namedParameterJdbcTemplate.update(DELETE_ALL_PERSONS_QUERY, new HashMap());
		System.out.println(count + " persons has been deleted.");
	}
}
