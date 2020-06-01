package com.computacion.service;

import com.computacion.model.TsscTimecontrol;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTimecontrolNotFoundException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;

public interface TsscTimecontrolService {
	
	public TsscTimecontrol getTimecontrol(long id) throws TsscTimecontrolNotFoundException;
	public void removeAll();
	public TsscTimecontrol createTimecontrol(TsscTimecontrol timecontrol, long gameId, long topicId) throws TsscGameNotFoundException, TsscTopicNotFoundException;
	public TsscTimecontrol updateTimecontrol(TsscTimecontrol timecontrol, long gameId, long topicId)
			throws TsscTimecontrolNotFoundException, TsscGameNotFoundException, TsscTopicNotFoundException;
	

}
