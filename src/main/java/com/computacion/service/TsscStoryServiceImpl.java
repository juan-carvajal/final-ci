package com.computacion.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.dao.StoryDao;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscStoryException;
import com.computacion.model.exceptions.TsscStoryNotFound;
import com.computacion.repository.TsscStoryRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TsscStoryServiceImpl implements TsscStoryService {

	private StoryDao storyDao;
	private TsscGameService gameService;

	public TsscStoryServiceImpl(StoryDao repo, TsscGameService service) {
		this.gameService = service;
		this.storyDao = repo;
	}

	@Override
	public TsscStory getStory(long id) throws TsscStoryNotFound {

			var result = this.storyDao.findById(id);

			if (result==null) {
				throw new TsscStoryNotFound(id);
			} else {

				return result;
			}


	}

	@Override
	public TsscStory createStory(TsscStory story, long gameID) throws TsscGameNotFoundException, TsscStoryException {
		// TODO Auto-generated method stub
		if (story != null) {
			if (story.getBusinessValue().compareTo(BigDecimal.ZERO) == 1
					&& story.getPriority().compareTo(BigDecimal.ZERO) == 1
					&& story.getInitialSprint().compareTo(BigDecimal.ZERO) == 1) {

				TsscGame game = this.gameService.getGame(gameID);
				story.setTsscGame(game);
				return this.storyDao.save(story);
			} else {
				throw new TsscStoryException(story);
			}
		} else {
			throw new TsscStoryException(story);
		}

	}

	@Override
	public TsscStory updateStory(TsscStory story, long gameID)
			throws TsscStoryException, TsscGameNotFoundException, TsscStoryNotFound {
		// TODO Auto-generated method stub
		if (story != null) {
			if (story.getBusinessValue().compareTo(BigDecimal.ZERO) == 1
					&& story.getPriority().compareTo(BigDecimal.ZERO) == 1
					&& story.getInitialSprint().compareTo(BigDecimal.ZERO) == 1) {
				TsscStory story1 = this.getStory(story.getId());
				TsscGame game = this.gameService.getGame(gameID);
				story.setTsscGame(game);
				return this.storyDao.update(story);
			} else {
				throw new TsscStoryException(story);
			}
		} else {
			throw new TsscStoryException(story);
		}
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		this.storyDao.removeAll();
	}

}
