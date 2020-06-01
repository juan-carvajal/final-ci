package com.computacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.dao.TimecontrolDao;
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTimecontrolNotFoundException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TsscTimecontrolServiceImpl implements TsscTimecontrolService{
	
	@Autowired
	public TimecontrolDao timecontrolDao;
	@Autowired
	public TsscGameService gameService;
	@Autowired
	public TsscTopicService topicService;

	@Override
	public TsscTimecontrol getTimecontrol(long id) throws TsscTimecontrolNotFoundException {
		
		var timecontrol=timecontrolDao.findById(id);
		if(timecontrol==null) {
			throw new TsscTimecontrolNotFoundException(id);
		}else {
			return timecontrol;
		}
		
	}

	@Override
	public TsscTimecontrol createTimecontrol(TsscTimecontrol timecontrol, long gameId, long topicId) throws TsscGameNotFoundException, TsscTopicNotFoundException {
		
		var game=gameService.getGame(gameId);
		var topic=topicService.getTopic(topicId);
		timecontrol.setTsscGame(game);
		timecontrol.setTsscTopic(topic);
		return this.timecontrolDao.save(timecontrol);
		
		
	}

	@Override
	public TsscTimecontrol updateTimecontrol(TsscTimecontrol timecontrol, long gameId, long topicId) throws TsscTimecontrolNotFoundException,TsscGameNotFoundException, TsscTopicNotFoundException {
		var previous=timecontrolDao.findById(timecontrol.getId());
		var game=gameService.getGame(gameId);
		var topic=topicService.getTopic(topicId);
		timecontrol.setTsscGame(game);
		timecontrol.setTsscTopic(topic);
		if(previous==null) {
			throw new TsscTimecontrolNotFoundException(timecontrol.getId());
		}else {
			return timecontrolDao.save(timecontrol);
		}
		
	}

	@Override
	public void removeAll() {
		timecontrolDao.removeAll();		
	}

}
