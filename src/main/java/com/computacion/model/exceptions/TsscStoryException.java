package com.computacion.model.exceptions;

import com.computacion.model.TsscStory;

public class TsscStoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6023608869144900217L;

	// valor de negocio, el sprint
	// inicial y la prioridad sean mayores que cero
	public TsscStoryException(TsscStory story) {
		if (story == null) {
			this.initCause(new Exception("Specified story can not be null."));
		} else {
			this.initCause(new IllegalArgumentException(
					"Check that businessValue,initialSprint and priority are greater than 0."));
		}
	}
}
