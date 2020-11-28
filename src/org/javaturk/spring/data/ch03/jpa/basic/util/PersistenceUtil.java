package org.javaturk.spring.data.ch03.jpa.basic.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class PersistenceUtil {

	private static String persistenceUnitName;
	private static Properties properties = new Properties();
	private static String provider;
	private static EntityManagerFactory entityManagerFactory;
	
	//To load properties
	static{
		try(FileReader reader = new FileReader("/Users/akin/Java/Eclipse/202003/workspace/Spring WS/Spring Data/src/org/javaturk/spring/data/ch03/jpa/basic/resources/jpa.properties")) {
			properties.load(reader);
		} catch (FileNotFoundException e) {
			System.out.println("Properties file not found!");
		} catch (IOException e) {
			System.out.println("Problem when reading properties file!");
		}
	}
	
	/**
	 * Sets the persistence unit name so that by calling <code>getEntityManager()</code> a new EntityManager instance can be received.
	 * @param persistenceUnitName
	 */
	public static void setPersistenceUnitName(String persistenceUnitName){
		PersistenceUtil.persistenceUnitName = persistenceUnitName;
		
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
		
		String databaseActionString = properties.getProperty("javax.persistence.schema-generation.database.action");
		String databaseAction = "--";
		if(databaseActionString != null)
			databaseAction = databaseActionString;
		
		Set<Object> keys = properties.keySet();
		
		System.out.format("******************************************************************************\n");
		System.out.format("* %50s %20s \n", "Creating the persistence unit with following properties", "*");
		System.out.format("* %-20s %-50s %4s \n", "Unit name:", persistenceUnitName, "*");
		System.out.format("* %-20s %-50s %4s \n", "Provider:", properties.getProperty("javax.persistence.provider"), "*");
		System.out.format("* %-20s %-50s %4s \n", "URL:", properties.getProperty("javax.persistence.jdbc.url"), "*");
		System.out.format("* %-20s %-50s %4s \n", "Username:", properties.getProperty("javax.persistence.jdbc.user"), "*");
		System.out.format("* %-20s %-50s %4s \n", "Password:", properties.getProperty("javax.persistence.jdbc.password"), "*");
		System.out.format("* %-20s %-50s %4s \n", "Driver:", properties.getProperty("javax.persistence.jdbc.driver"), "*");
		System.out.format("* %-20s %-50s %4s \n", "Database action:", databaseAction, "*");
		System.out.format("* %-70s %5s \n", "       *  *  *   Other   Properties   *  *  *  ", "*");
		
		for(Object key : keys) {
			String stringKey = (String) key;
			if(!stringKey.startsWith("javax"))
				System.out.format("* %-50s %-20s %4s \n", stringKey + ":", properties.getProperty(stringKey), "*");
		}
		System.out.format("******************************************************************************\n");
	}

	/**
	 * Get the name of the persistence unit.
	 * @return Name of the persistence unit
	 */
	public static String getPersistenceUnitName() {
		return persistenceUnitName;
	}
	
	/**
	 * Get EntityManagerFactory created with the persistence unit data.
	 * @return EntityManagerFactory instance created.
	 */
	public static EntityManagerFactory getEntityManagerFactory(){
		return entityManagerFactory;
	}
	
	/**
	 * Use this method in multi-threaded environment. It is synchronized.
	 * @param persistenceUnitName Persistence unit name for which an EntityManager instance is created.
	 * @return EntityManager instance created.
	 */
	public static synchronized EntityManagerFactory getEntityManagerFactory(String persistenceUnitName){
		setPersistenceUnitName(persistenceUnitName);
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	/**
	 * This method is about turning on/off EclipseLink's extension for weaving in order to enable 
	 * lazy fetching for example for Basic types.
	 * @param b true to enable lazy loading.
	 */
//	public static void turnOnEclipseLinkWeaving(boolean b) {
//		properties.put(PersistenceUnitProperties.WEAVING, "false");
//	}
	
	/**
	 * Just to test the class quickly.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("In main");
		setPersistenceUnitName("ch01");
	}
	
	public static void close() {
		entityManagerFactory.close();
	}
}
