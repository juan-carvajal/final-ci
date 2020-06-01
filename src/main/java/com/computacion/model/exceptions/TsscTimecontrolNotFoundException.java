package com.computacion.model.exceptions;

public class TsscTimecontrolNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8260546472864653933L;

	public TsscTimecontrolNotFoundException(long id) {
		super("There is no timecontrol with id "+id);
	}

}
