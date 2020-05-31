package com.computacion.model.exceptions;

import com.computacion.model.TsscGame;

public class TsscGameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6888209928421276544L;
	
	public TsscGameException(TsscGame game) {
		if(game==null) {
			this.initCause(new Exception("Specified game can not be null."));
			
		}else {
			this.initCause(new IllegalArgumentException("Size of tsscGroups and tsscSpring must be grater than 0."));
		}
	}

}
