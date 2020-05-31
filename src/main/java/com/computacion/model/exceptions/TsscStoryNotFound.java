package com.computacion.model.exceptions;

public class TsscStoryNotFound extends Exception {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7569039864507767762L;

	public TsscStoryNotFound(long id) {
		super("There is not TsscStory with id "+id+".");
	}
}
