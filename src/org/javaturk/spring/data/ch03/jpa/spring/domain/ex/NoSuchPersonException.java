package org.javaturk.spring.data.ch03.jpa.spring.domain.ex;

public class NoSuchPersonException extends Exception{
	private static String message = "No such person found! Id: ";
	
	public NoSuchPersonException(int id) {
		super(message + id);
	}
}
