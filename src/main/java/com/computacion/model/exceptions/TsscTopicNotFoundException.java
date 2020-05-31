package com.computacion.model.exceptions;

public class TsscTopicNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3770433962340244558L;

	
	public TsscTopicNotFoundException(long id) {
		super("No TsscTopic with id "+id+" exists.");
	}
	
}
