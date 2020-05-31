package com.computacion.unit;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import com.computacion.dao.GameDao;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscGroup;
import com.computacion.model.TsscSprint;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;
import com.computacion.service.TsscGameService;
import com.computacion.service.TsscGameServiceImpl;
import com.computacion.service.TsscTopicService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TsscGameServiceTests {

	@Mock
	private GameDao gameDao;

	@Mock
	private TsscTopicService topicService;

	private TsscGameService gameService;

	@BeforeEach
	public void setUp() {
		this.gameService = new TsscGameServiceImpl(gameDao, topicService);
	}

	@Test
	public void TestGet() throws TsscGameNotFoundException {
		long mockId=554;
		TsscGame mockGame=new TsscGame();
		mockGame.setId(mockId);
		when(gameDao.findById(mockId)).thenReturn(mockGame);
		Assertions.assertTrue(mockId==gameService.getGame(mockId).getId());
	}
	
	@Test
	public void TestCreateFailNoTopic() {
		TsscGame game=new TsscGame();
game.setNGroups(0);
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.createGame(game);
		});
		verifyNoInteractions(gameDao);
	}
	
	@Test
	public void TestCreatePassNoTopic() throws TsscGameException {
		TsscGame game=new TsscGame();
		gameService.createGame(game);
		verify(gameDao).save(game);

	}
	
	@Test
	public void TestCreateNullNoTopic() {
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.createGame(null);
		});
		verifyNoInteractions(gameDao);
	}
	
	
	@Test
	public void TestUpdateFailNoTopic() throws TsscGameException {
		long mockID=553;
		TsscGame old=new TsscGame();
		old.setId(mockID);
		when(this.gameDao.findById(mockID)).thenReturn(old);
		TsscGame new_g=new TsscGame();
		new_g.setNGroups(0);
		new_g.setId(mockID);
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.updateGame(new_g);
		});


	}
	
	@Test
	public void testUpdateNotFoundNoTopic() {
		long mockID=553;
		TsscGame game=new TsscGame();
		game.setId(mockID);
		when(this.gameDao.findById(mockID)).thenReturn(null);
		Assertions.assertThrows(TsscGameNotFoundException.class, ()->{
			
			gameService.updateGame(game);
		});

		
	}
	
	@Test
	public void testUpdatePassNoTopic() throws TsscGameException, TsscGameNotFoundException {
		long mockID=553;
		TsscGame old=new TsscGame();
		old.setId(mockID);
		when(this.gameDao.findById(mockID)).thenReturn(old);
		TsscGame new_g=new TsscGame();
		new_g.setTsscGroups(new ArrayList<TsscGroup>());
		new_g.setTsscSprints(new ArrayList<TsscSprint>());
		new_g.addTsscGroup(new TsscGroup());
		new_g.addTsscSprint(new TsscSprint());
		new_g.setId(mockID);
		gameService.updateGame(new_g);
		verify(gameDao).save(new_g);
	}
	
	
	@Test
	public void testCreateUpdateTopicNotFoundTopic() throws TsscTopicNotFoundException {
		long topicId=254;
		TsscTopic mockTopic=new TsscTopic();
		mockTopic.setId(topicId);
		long gameID=554;
		TsscGame mockGame=new TsscGame();
		mockGame.setId(gameID);
		when(this.topicService.getTopic(topicId)).thenThrow(new TsscTopicNotFoundException(topicId));
//		when(this.gameRepo.findById(gameID)).thenReturn(Optional.of(mockGame));
		Assertions.assertThrows(TsscTopicNotFoundException.class, ()->{
			
			gameService.createGame(mockGame,topicId);
		});
		
	}
	
	
	@Test
	public void testCreatePassTopic() throws TsscTopicNotFoundException, TsscGameException {
		long topicId=254;
		TsscTopic mockTopic=new TsscTopic();
		mockTopic.setId(topicId);
		long gameID=554;
		TsscGame mockGame=new TsscGame();
		mockGame.setTsscGroups(new ArrayList<>());
		mockGame.setTsscSprints(new ArrayList<>());
		mockGame.addTsscGroup(new TsscGroup());
		mockGame.addTsscSprint(new TsscSprint());
		mockGame.setId(gameID);
		when(this.topicService.getTopic(topicId)).thenReturn(mockTopic);
//		when(this.gameRepo.findById(gameID)).thenReturn(Optional.of(mockGame));
		gameService.createGame(mockGame,topicId);
		verify(this.gameDao).save(mockGame);
		verify(this.topicService).getTopic(topicId);

		
	}
	
	
	@Test
	public void testUpdatePassTopic() throws TsscTopicNotFoundException, TsscGameException, TsscGameNotFoundException {
		long topicId=254;
		TsscTopic mockTopic=new TsscTopic();
		mockTopic.setId(topicId);
		long gameID=554;
		TsscGame mockGame=new TsscGame();
		mockGame.setTsscGroups(new ArrayList<>());
		mockGame.setTsscSprints(new ArrayList<>());
		mockGame.addTsscGroup(new TsscGroup());
		mockGame.addTsscSprint(new TsscSprint());
		mockGame.setId(gameID);
		when(this.topicService.getTopic(topicId)).thenReturn(mockTopic);
		when(this.gameDao.findById(gameID)).thenReturn(mockGame);
		gameService.updateGame(mockGame,topicId);
		verify(this.gameDao).save(mockGame);
		verify(this.topicService).getTopic(topicId);

		
	}
	
	
	@Test
	public void testCreateFailTopic() throws TsscTopicNotFoundException, TsscGameException {
		long topicId=254;
		TsscTopic mockTopic=new TsscTopic();
		mockTopic.setId(topicId);
		long gameID=554;
		TsscGame mockGame=new TsscGame();
mockGame.setNGroups(0);

		mockGame.setId(gameID);
		when(this.topicService.getTopic(topicId)).thenReturn(mockTopic);
//		when(this.gameRepo.findById(gameID)).thenReturn(Optional.of(mockGame));
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.createGame(mockGame,topicId);
		});
		verify(this.topicService).getTopic(topicId);

		
	}
	
	
	@Test
	public void testUpdateFailTopic() throws TsscTopicNotFoundException, TsscGameException {
		long topicId=254;
		TsscTopic mockTopic=new TsscTopic();
		mockTopic.setId(topicId);
		long gameID=554;
		TsscGame mockGame=new TsscGame();
mockGame.setNGroups(0);
		mockGame.setId(gameID);
		when(this.topicService.getTopic(topicId)).thenReturn(mockTopic);
		when(this.gameDao.findById(gameID)).thenReturn(mockGame);
		Assertions.assertThrows(TsscGameException.class, ()->{
			
			gameService.updateGame(mockGame,topicId);
		});
		verify(this.topicService).getTopic(topicId);

		
	}
	
	@Test
	public void testCreate2() throws TsscTopicNotFoundException, TsscGameException {
		long topicId=254;
		TsscTopic mockTopic=new TsscTopic();
		mockTopic.setId(topicId);
		mockTopic.setTsscStories(new ArrayList<>());
		mockTopic.setTsscTimeControls(new ArrayList<>());
		for (int i = 0; i < 10; i++) {
			var s=new TsscStory();
			s.setId(i);;
			mockTopic.addTsscStory(s);
		}
		
		for (int i = 0; i < 10; i++) {
			var tc=new TsscTimecontrol();
			tc.setId(i);;
			mockTopic.addTimeControl(tc);
		}
		long gameID=554;
		TsscGame mockGame=new TsscGame();
		mockGame.setTsscGroups(new ArrayList<>());
		mockGame.setTsscSprints(new ArrayList<>());
		mockGame.setTsscTimecontrol(new ArrayList<>());
		mockGame.setTsscStories(new ArrayList<>());
		mockGame.addTsscGroup(new TsscGroup());
		mockGame.addTsscSprint(new TsscSprint());
		mockGame.setId(gameID);
		when(this.topicService.getTopic(topicId)).thenReturn(mockTopic);
		//when(this.gameRepo.findById(gameID)).thenReturn(Optional.of(mockGame));
		this.gameService.createGame2(mockGame, topicId);
		for (int i = 0; i < 10; i++) {
			Assertions.assertTrue(mockGame.getTsscTimecontrols().get(i).getId()==mockTopic.getTsscTimeControls().get(i).getId());
			Assertions.assertTrue(mockGame.getTsscStories().get(i).getId()==mockTopic.getTsscStories().get(i).getId());
		}

		
		
	}
	
	
	

}
