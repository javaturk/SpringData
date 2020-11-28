package org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.dao;

import java.util.List;

import org.javaturk.spring.data.ch02.jdbc.common.data.PersonDAO;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Factory;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.callback.PersonPreparedStatementIdAndDobSetter;
import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.callback.PersonPreparedStatementIdSetter;
import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.callback.PersonPreparedStatementSetter;
import org.javaturk.spring.data.ch02.spring.jdbcTemplate.person.callback.PersonRowMapper;
import org.javaturk.spring.data.ch02.jdbc.common.domain.ex.NoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PersonJdbcTemplateDAO implements PersonDAO {
	private static final String SAVE_PERSON_QUERY = "INSERT INTO PERSONS VALUES(?,?,?,?)";
	private static final String COUNT_QUERY = "SELECT COUNT(*) FROM PERSONS";
	private static final String RETRIEVE_PERSON_QUERY = "SELECT * FROM PERSONS WHERE ID = ?";
	private static final String RETRIEVE_ALL_PERSONS_QUERY = "SELECT * FROM PERSONS";
	private static final String UPDATE_PERSONDOB_QUERY = "UPDATE PERSONS SET DATEOFBIRTH = ? WHERE ID = ?";
	private static final String DELETE_PERSON_QUERY = "DELETE FROM PERSONS WHERE ID = ?";
	private static final String DELETE_ALL_PERSONS_QUERY = "DELETE FROM PERSONS";

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	@Override
//	public void savePerson(Person person) {
//		int count = jdbcTemplate.update(SAVE_PERSON_QUERY, person.getId(), person.getFirstName(), person.getLastName(), person.getDobAsSQlDate());
//		if (count == 1)
//			System.out.println("Person has been saved.");
//		else
//			System.out.println("Problem when saving Person.");
//	}

	@Override
	public void savePerson(Person person) {
		System.out.println("\nSaving person:" + person);
		int count = jdbcTemplate.update(SAVE_PERSON_QUERY, new PersonPreparedStatementSetter(person));
		if (count == 1)
			System.out.println("Person saved.");
		else
			System.out.println("Problem when saving Person.");
	}

	@Override
	public int retrievePersonCount() {
		return jdbcTemplate.queryForObject(COUNT_QUERY, Integer.class);
	}

//	@Override
//	public Person retrievePerson(int id) throws NoSuchPersonException {
//		Person person = (Person) jdbcTemplate.query(RETRIEVE_PERSON_QUERY, new PersonRowMapper(), id);
//		if (person.getId() == 0)
//			throw new NoSuchPersonException(person.getId());
//		return person;
//	}
	
	@Override
	public Person retrievePerson(int id) throws NoSuchPersonException {
		Person person = jdbcTemplate.queryForObject(RETRIEVE_PERSON_QUERY, new PersonRowMapper(), id);
		return person;
	}

	@Override
	public List<Person> retrieveAllPersons() {
		return (List<Person>) jdbcTemplate.query(RETRIEVE_ALL_PERSONS_QUERY, new PersonRowMapper());
	}

	@Override
	public void updatePersonDOB(Person person) {
		int count = jdbcTemplate.update(UPDATE_PERSONDOB_QUERY, new PersonPreparedStatementIdAndDobSetter(person));
		if (count == 1)
			System.out.println("Person has been updated.");
		else
			System.out.println("Problem when updating Person.");
	}

	@Override
	public void deletePerson(Person person) {
		jdbcTemplate.update(DELETE_PERSON_QUERY, new PersonPreparedStatementIdSetter(person));
	}

	@Override
	public void deleteAllPersons() {
		int count = jdbcTemplate.update(DELETE_ALL_PERSONS_QUERY);
		System.out.println(count + " persons has been deleted.");
	}
}
