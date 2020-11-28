package org.javaturk.spring.data.ch02.jdbc.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.javaturk.spring.data.ch02.jdbc.common.data.PersonDAO;
import org.javaturk.spring.data.ch02.jdbc.common.domain.Person;
import org.javaturk.spring.data.ch02.jdbc.common.domain.ex.NoSuchPersonException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonJdbcDataSourceDAO implements PersonDAO {
	private static final String SAVE_PERSON_QUERY = "INSERT INTO PERSONS VALUES(?,?,?,?)";
	private static final String COUNT_QUERY = "SELECT COUNT(*) AS COUNT FROM PERSONS";
	private static final String RETRIEVE_PERSON_QUERY = "SELECT * FROM PERSONS WHERE ID = ?";
	private static final String RETRIEVE_ALL_PERSONS_QUERY = "SELECT * FROM PERSONS";
	private static final String UPDATE_PERSONDOB_QUERY = "UPDATE PERSONS SET DATEOFBIRTH = ? WHERE ID = ?";
	private static final String DELETE_PERSON_QUERY = "DELETE FROM PERSONS WHERE ID = ?";
	private static final String DELETE_ALL_PERSONS_QUERY = "DELETE FROM PERSONS";
	
	// Don't do that. It needs to be refreshed every time a connection is needed
	// because of the fact that
	// connection object is closed after it is used. So DataSource is injected instead.
//	@Autowired
//	private Connection connection;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void savePerson(Person person) {
		System.out.println("\nSaving person:" + person);
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SAVE_PERSON_QUERY);
			stmt.setInt(1, person.getId());
			stmt.setString(2, person.getFirstName());
			stmt.setString(3, person.getLastName());
			stmt.setDate(4, person.getDobAsSQlDate());
			int updateCount = stmt.executeUpdate();
			if (updateCount != 1)
				System.out.println("Problem with saving the person.");
			else
				System.out.println("Person saved!");
		} catch (SQLException e) {
			System.out.println("Problem with statement: " + e.getMessage());
			e.printStackTrace();
		} finally {
			returnConnection(conn);
		}
	}

	@Override
	public Person retrievePerson(int id) throws NoSuchPersonException {
		System.out.println("\nRetrieving the person with id = " + id);
		Person personRetrieved = null;
		Connection conn = getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(RETRIEVE_PERSON_QUERY);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				Date dob = rs.getDate("DATEOFBIRTH");
				personRetrieved = new Person(id, firstName, lastName, dob);
			}
			if (personRetrieved == null)
				throw new NoSuchPersonException(id);
		} catch (SQLException e) {
			System.out.println("Problem with statement: " + e.getMessage());
			e.printStackTrace();
		} finally {
			returnConnection(conn);
		}
		return personRetrieved;
	}
	
	@Override
	public int retrievePersonCount() {
		int personCount = -1;
		Connection conn = getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(COUNT_QUERY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				personCount = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			System.out.println("Problem with statement: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			returnConnection(conn);
		}
		return personCount;
	}

	@Override
	public List<Person> retrieveAllPersons() {
		System.out.println("\nRetrieving all of the persons.");
		List<Person> persons = new ArrayList();
		Connection conn = getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(RETRIEVE_ALL_PERSONS_QUERY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				Date dob = rs.getDate("DATEOFBIRTH");
				Person personRetrieved = new Person(id, firstName, lastName, dob);
				persons.add(personRetrieved);
			}
		} catch (SQLException e) {
			System.out.println("Problem with statement: " + e.getMessage());
			e.printStackTrace();
		} finally {
			returnConnection(conn);
		}
		System.out.println(persons.size() + " persons retrieved.");
		return persons;
	}

	@Override
	public void updatePersonDOB(Person person) {
		System.out.println("\nUpdating a person's date of birth:" + person);
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_PERSONDOB_QUERY);
			stmt.setDate(1, person.getDobAsSQlDate());
			stmt.setInt(2, person.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount != 1) {
				System.out.println("No such person with id = " + person.getId());
				System.out.println("No person updated!");
			} else
				System.out.println("Person updated!");
		} catch (SQLException e) {
			System.out.println("Problem with statement: " + e.getMessage());
			e.printStackTrace();
		} finally {
			returnConnection(conn);
		}
	}

	@Override
	public void deletePerson(Person person) {
		System.out.println("\nDeleting a person:" + person);
		Connection conn = getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(DELETE_PERSON_QUERY);
			stmt.setInt(1, person.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount != 1) {
				System.out.println("No such person with id = " + person.getId());
				System.out.println("No person deleted!.");
			} else
				System.out.println("Person deleted!");
		} catch (SQLException e) {
			System.out.println("Problem with statement: " + e.getMessage());
			e.printStackTrace();
		} finally {
			returnConnection(conn);
		}
	}

	@Override
	public void deleteAllPersons() {
		System.out.println("\nDeleting all persons.");
		Connection conn = getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(DELETE_ALL_PERSONS_QUERY);
			int updateCount = stmt.executeUpdate();
			System.out.println(updateCount + " persons deleted!");
		} catch (SQLException e) {
			System.out.println("Problem with statement: " + e.getMessage());
			e.printStackTrace();
		} finally {
			returnConnection(conn);
		}
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void returnConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Problem with closing the connection: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
