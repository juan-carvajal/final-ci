package com.computacion.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.dao.GameDao;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TsscGameServiceImpl implements TsscGameService{
	
	
	private GameDao gameDao;
	private TsscTopicService topicService;
	
	public TsscGameServiceImpl(GameDao repo,TsscTopicService service) {
		this.gameDao=repo;
		this.topicService=service;
	}

	@Override
	public TsscGame getGame(long id) throws TsscGameNotFoundException {
		var result=this.gameDao.findById(id);
		if (result==null) {
			throw new TsscGameNotFoundException(id);
			
		}else {
			return result;	}
	}

	@Override
	public TsscGame createGame(TsscGame game) throws TsscGameException {
			if(game!=null) {
				if(game.getNGroups()<=0 || game.getNSprints()<=0) {
					throw new TsscGameException(game);
				}else {
					return this.gameDao.save(game);
				}
			}else {
				throw new TsscGameException(game);
			}
			

	}

	@Override
	public TsscGame updateGame(TsscGame game) throws TsscGameException, TsscGameNotFoundException {
		if(game!=null) {
			this.getGame(game.getId());
			if(game.getNGroups()<=0 || game.getNSprints()<=0) {
				throw new TsscGameException(game);
			}else {
				return this.gameDao.save(game);
			}
			
		}else {
			throw new TsscGameException(game);
		}
	}

	@Override
	public TsscGame createGame(TsscGame game, long topicID) throws TsscTopicNotFoundException, TsscGameException {
		// TODO Auto-generated method stub
		if(game!=null) {
			
			TsscTopic topic=this.topicService.getTopic(topicID);
			game.setTsscTopic(topic);
			return this.createGame(game);
		}else {
			throw new TsscGameException(game);
		}
	}

	@Override
	public TsscGame updateGame(TsscGame game, long topicID) throws TsscTopicNotFoundException, TsscGameException, TsscGameNotFoundException {
		if(game!=null) {
			TsscTopic topic=this.topicService.getTopic(topicID);
			game.setTsscTopic(topic);
			return this.updateGame(game);
		}else {
			throw new TsscGameException(game);
		}
	}

	@Override
	public TsscGame createGame2(TsscGame game, long topicID) throws TsscTopicNotFoundException, TsscGameException {
		// TODO Auto-generated method stub
		if(game!=null) {

			TsscTopic topic=this.topicService.getTopic(topicID);
			System.out.println("Entered topic with: "+topic.getTsscStories().size());
			game.setTsscTopic(topic);
			for(TsscStory story:topic.getTsscStories()) {
				game.addTsscStory(story);
			}
			
			for(var tc:topic.getTsscTimeControls()) {
				game.addTsscTimecontrol(tc);
			}
			
			return this.createGame(game);
		}else {
			throw new TsscGameException(game);
		}
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		this.gameDao.removeAll();	}

}
