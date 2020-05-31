package com.computacion.service;

import com.computacion.model.TsscStory;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscStoryException;
import com.computacion.model.exceptions.TsscStoryNotFound;

public interface TsscStoryService {

	
	
	public TsscStory getStory(long id) throws TsscStoryNotFound;
	
	
	public TsscStory createStory(TsscStory story, long gameID) throws TsscGameNotFoundException, TsscStoryException;
	
	
	public TsscStory updateStory(TsscStory story, long gameID) throws TsscStoryException, TsscGameNotFoundException, TsscStoryNotFound;
	public void removeAll();
}
