package com.computacion.model.exceptions;

import com.computacion.model.TsscTopic;

public class TsscTopicException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8735770605695597718L;


	public TsscTopicException(TsscTopic topic) {
		if(topic==null) {
			this.initCause(new Throwable("TsscTopic can not be null"));
		}else {
			this.initCause(new IllegalArgumentException("Check that defaultGroups and defaultSprints are greater than 0."));
		}
	}
	
}
