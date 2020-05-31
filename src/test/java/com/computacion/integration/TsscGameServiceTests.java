package com.computacion.integration;



import java.math.BigDecimal;
import java.util.ArrayList;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscGroup;
import com.computacion.model.TsscSprint;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscStoryException;
import com.computacion.model.exceptions.TsscStoryNotFound;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscSprintRepository;
import com.computacion.repository.TsscTimecontrolRepository;
import com.computacion.service.TsscGameService;
import com.computacion.service.TsscStoryService;
import com.computacion.service.TsscTopicService;

@SpringBootTest
@Rollback(true)
public class TsscGameServiceTests {



	private TsscTopicService topicService;

	private TsscGameService gameService;
	private TsscTimecontrolRepository timecontrolRepo;
	private TsscStoryService storyService;
	private long gameId;
	private long topicId;
	
	@Autowired
	public TsscGameServiceTests(TsscTopicService topicService,TsscGameService gameService,TsscTimecontrolRepository timecontrolRepo,TsscStoryService storyService) {
		this.topicService=topicService;
		this.gameService=gameService;
		this.storyService=storyService;
		this.timecontrolRepo=timecontrolRepo;
	}

	@BeforeEach
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setUp() throws TsscGameException, TsscTopicException {
		try {
			this.gameService.removeAll();
			this.topicService.removeAll();
			this.storyService.removeAll();
			this.timecontrolRepo.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
		}

		var game=new TsscGame();
		this.gameId=this.gameService.createGame(game).getId();
		var topic=new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topic.setTsscStories(new ArrayList<>());
		topic.setTsscTimeControls(new ArrayList<>());
		this.topicId=this.topicService.createTopic(topic).getId();
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestGet() throws TsscGameNotFoundException {

		Assertions.assertTrue(this.gameId==gameService.getGame(this.gameId).getId());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestCreateFailNoTopic() {
		TsscGame game=new TsscGame();
		game.setNGroups(0);
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.createGame(game);
		});

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestCreatePassNoTopic() throws TsscGameException {
		TsscGame game=new TsscGame();
		var created=gameService.createGame(game);
		Assertions.assertTrue(created !=null);

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestCreateNullNoTopic() {
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.createGame(null);
		});

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestUpdateFailNoTopic() throws TsscGameException {

		TsscGame new_g=new TsscGame();
		new_g.setNGroups(0);
		new_g.setId(this.gameId);
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.updateGame(new_g);
		});


	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateNotFoundNoTopic() {
		long mockID=553;
		TsscGame game=new TsscGame();
		game.setId(mockID);

		Assertions.assertThrows(TsscGameNotFoundException.class, ()->{
			
			gameService.updateGame(game);
		});

		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdatePassNoTopic() throws TsscGameException, TsscGameNotFoundException {

		TsscGame new_g=this.gameService.getGame(this.gameId);
		new_g.setNGroups(1000);
		var updated=gameService.updateGame(new_g);
		Assertions.assertTrue(updated.getId()==new_g.getId());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreateTopicNotFoundTopic() throws TsscTopicNotFoundException {
		long topicId=254;
		long gameID=554;
		TsscGame newGame=new TsscGame();
		newGame.setId(gameID);

		Assertions.assertThrows(TsscTopicNotFoundException.class, ()->{
			
			gameService.createGame(newGame,topicId);
		});
		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreatePassTopic() throws TsscTopicNotFoundException, TsscGameException {



		TsscGame newGame=new TsscGame();
		var created=gameService.createGame(newGame,topicId);

Assertions.assertTrue(created !=null);
		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdatePassTopic() throws TsscTopicNotFoundException, TsscGameException, TsscGameNotFoundException {


		TsscGame newGame=this.gameService.getGame(this.gameId);

		newGame.setNGroups(45);

		var updated=gameService.updateGame(newGame,this.topicId);
		Assertions.assertTrue(updated.getId()==newGame.getId());

		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreateFailTopic() throws TsscTopicNotFoundException, TsscGameException {

		TsscGame newGame=new TsscGame();
		newGame.setNGroups(0);


		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.createGame(newGame,this.topicId);
		});


		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateFailTopic() throws TsscTopicNotFoundException, TsscGameException {

		TsscGame newGame=new TsscGame();
		newGame.setNGroups(0);
		newGame.setId(this.gameId);

		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.updateGame(newGame,topicId);
		});


		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreate2() throws TsscTopicNotFoundException, TsscGameException, TsscGameNotFoundException, TsscStoryException, TsscTopicException, TsscStoryNotFound {

		var topic=this.topicService.getTopic(this.topicId);
		
		for (int i = 0; i < 10; i++) {
			var s=new TsscStory();
			s.setBusinessValue(BigDecimal.TEN);
			s.setPriority(BigDecimal.TEN);
			s.setInitialSprint(BigDecimal.TEN);
			s=this.storyService.createStory(s, this.gameId);
			topic.addTsscStory(s);
			this.storyService.updateStory(s, this.gameId);
		}
		
		for (int i = 0; i < 10; i++) {
			var tc=new TsscTimecontrol();
			tc=this.timecontrolRepo.save(tc);
			topic.addTimeControl(tc);
			timecontrolRepo.save(tc);
		}
		topic=this.topicService.updateTopic(topic);
		System.out.println("Updated topic with: "+topic.getTsscStories().size());
		TsscGame newGame=new TsscGame();
		newGame.setTsscTimecontrol(new ArrayList<>());
		newGame.setTsscStories(new ArrayList<>());

		

		newGame=this.gameService.createGame2(newGame, topicId);
		for (int i = 0; i < 10; i++) {
			Assertions.assertTrue(newGame.getTsscTimecontrols().get(i).getId()==topic.getTsscTimeControls().get(i).getId());
			Assertions.assertTrue(newGame.getTsscStories().get(i).getId()==topic.getTsscStories().get(i).getId());
		}

		
		
	}
	
	
	

}
