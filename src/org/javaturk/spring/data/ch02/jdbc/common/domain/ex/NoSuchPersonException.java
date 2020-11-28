package org.javaturk.spring.data.ch02.jdbc.common.domain.ex;

public class NoSuchPersonException extends Exception{
	private static String message = "No such person found! Id: ";
	
	public NoSuchPersonException(int id) {
		super(message + id);
	}
}
