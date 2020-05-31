package com.computacion.service;

import com.computacion.model.TsscGame;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;

public interface TsscGameService {

	
	public TsscGame getGame(long id) throws TsscGameNotFoundException;
	public TsscGame createGame(TsscGame game) throws TsscGameException;
	public TsscGame updateGame(TsscGame game) throws TsscGameException, TsscGameNotFoundException;
	public TsscGame createGame(TsscGame game, long topicID) throws TsscTopicNotFoundException, TsscGameException;
	public TsscGame updateGame(TsscGame game , long topicID) throws TsscTopicNotFoundException, TsscGameException, TsscGameNotFoundException;
	
	
	public TsscGame createGame2(TsscGame game, long topicID) throws TsscTopicNotFoundException, TsscGameException;
	public void removeAll();
}
