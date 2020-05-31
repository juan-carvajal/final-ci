package com.computacion.service;

import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;

public interface TsscTopicService {

	
	public TsscTopic getTopic(long id) throws TsscTopicNotFoundException;
	public TsscTopic createTopic(TsscTopic topic) throws TsscTopicException;
	public TsscTopic updateTopic(TsscTopic topic) throws TsscTopicException, TsscTopicNotFoundException;
	public void removeAll();
}
