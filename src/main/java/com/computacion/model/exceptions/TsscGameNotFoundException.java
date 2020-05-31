package com.computacion.model.exceptions;

public class TsscGameNotFoundException extends Exception{/**
	 * 
	 */
	private static final long serialVersionUID = -3369076459263414211L;

	public TsscGameNotFoundException(long id) {
		super("There is not TsscGame with id "+id+".");
	}
	
	
}


