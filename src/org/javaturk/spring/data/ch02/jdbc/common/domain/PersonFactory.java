package org.javaturk.spring.data.ch02.jdbc.common.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PersonFactory implements Factory{
	private static PersonFactory personFactory = new PersonFactory();
	private static Random random = new Random();

	private static String[] firstnames = { "Ali", "Ayse", "Bahar", "Cem", "Demet", "Eylem", "Faruk", "Fatma", "Ganime",
			"Haydar", "Halil", "Ismail", "Jale", "Kemal", "Leman", "Mustafa", "Nedim", "Nilufer", "Selim", "Selman",
			"Sevda", "Tarik", "Teoman", "Yeliz", "Zuhal" };
	private static String[] lastnames = { "Arabaci", "Aslan", "Batur", "Bulut", "Ceviz", "Karli", "Lale", "Ozturk",
			"Pasa", "Sari", "Serim", "Telli", "Torbaci", "Yazar", "Zafer" };

	private PersonFactory() {}
	
	public static PersonFactory getInstance() {
		return personFactory;
	}
	
	@Override
	public Person createPerson() {
		Person person = new Person();
		person.setId(createId());
		person.setFirstName(createFirstName());
		person.setLastName(createLastName());
		person.setDob(createDob());
		return person;
	}

	private int createId() {
		int randomInt = random.nextInt();
		if (randomInt < 0)
			randomInt = -randomInt;
		return randomInt;
	}

	private String createFirstName() {
		int randomInt = random.nextInt(25);
		return firstnames[randomInt];
	}

	private String createLastName() {
		int randomInt = random.nextInt(15);
		return lastnames[randomInt];
	}

	private Date createDob() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1950 + random.nextInt(60), random.nextInt(11), random.nextInt(30));
		return calendar.getTime();
	}
}
