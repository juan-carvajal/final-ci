package com.computacion.unit;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.computacion.dao.StoryDao;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscStoryException;
import com.computacion.model.exceptions.TsscStoryNotFound;
import com.computacion.repository.TsscStoryRepository;
import com.computacion.service.TsscGameService;
import com.computacion.service.TsscStoryService;
import com.computacion.service.TsscStoryServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TsscStoryServiceTests {

	@Mock
	private StoryDao storyDao;
	@Mock
	private TsscGameService gameService;
	
	
	private TsscStoryService storyService;

	@BeforeEach
	public void setup() {
//		System.out.println(this.storyRepository);
//		System.out.println(this.gameService);
		this.storyService = new TsscStoryServiceImpl(this.storyDao, this.gameService);

	}

	@Test
	public void testGet() throws TsscStoryNotFound {
		long mockId = 485;
		var mockStory = new TsscStory();
		mockStory.setId(mockId);
		when(this.storyDao.findById(mockId)).thenReturn(mockStory);

			var result = storyService.getStory(mockId);
			Assertions.assertTrue(result.getId() == mockStory.getId());
			verify(this.storyDao).findById(mockId);


		when(this.storyDao.findById(mockId)).thenReturn(null);
		Assertions.assertThrows(TsscStoryNotFound.class, () -> {

			this.storyService.getStory(mockId);
		});
	}
	
	
	@Test
	public void createFailConstraints() throws TsscGameNotFoundException, TsscStoryException {
		long mockId=485;
		var mockStory=new TsscStory();
		mockStory.setId(mockId);
		mockStory.setBusinessValue(BigDecimal.ZERO);
		mockStory.setPriority(BigDecimal.ZERO);
		mockStory.setInitialSprint(BigDecimal.ZERO);
		long mockGameID=447;
		var mockGame=new TsscGame();
		mockGame.setId(mockGameID);
//		when(this.storyRepository.findById(mockId)).thenReturn(Optional.of(mockStory));
		Assertions.assertThrows(TsscStoryException.class, ()->{
			
			this.storyService.createStory(mockStory, mockGameID);
		});
		verifyNoInteractions(this.storyDao);


	}
	
	
	@Test
	public void createPass() throws TsscGameNotFoundException, TsscStoryException {
		long mockId=485;
		var mockStory=new TsscStory();
		mockStory.setId(mockId);
		mockStory.setBusinessValue(BigDecimal.TEN);
		mockStory.setPriority(BigDecimal.TEN);
		mockStory.setInitialSprint(BigDecimal.TEN);
		long mockGameID=447;
		var mockGame=new TsscGame();
		mockGame.setId(mockGameID);
		when(this.gameService.getGame(mockGameID)).thenReturn(mockGame);
		this.storyService.createStory(mockStory, mockGameID);
		verify(this.storyDao).save(mockStory);
		verify(this.gameService).getGame(mockGameID);

	}
	
	
	@Test
	public void createFailGameNotFound() throws TsscGameNotFoundException {
		long mockId=485;
		var mockStory=new TsscStory();
		mockStory.setId(mockId);
		mockStory.setBusinessValue(BigDecimal.TEN);
		mockStory.setPriority(BigDecimal.TEN);
		mockStory.setInitialSprint(BigDecimal.TEN);
		long mockGameID=447;
		when(this.gameService.getGame(mockGameID)).thenThrow(new TsscGameNotFoundException(mockGameID));
		Assertions.assertThrows(TsscGameNotFoundException.class, ()->{
			
			this.storyService.createStory(mockStory, mockGameID);
		});
		verifyNoInteractions(this.storyDao);
	}
	
	
	@Test
	public void createFailNull() {
Assertions.assertThrows(TsscStoryException.class, ()->{
			long dummy=154;
			this.storyService.createStory(null, dummy);
		});
	}
	
	
	@Test
	public void updateFailContraints() throws TsscGameNotFoundException {
		long storyId=445;
		var oldStory=new TsscStory();
		oldStory.setId(storyId);
//		when(this.storyRepository.findById(storyId)).thenReturn(Optional.of(oldStory));
		var newStory=new TsscStory();
		newStory.setBusinessValue(BigDecimal.TEN);
		newStory.setPriority(BigDecimal.ZERO);
		newStory.setInitialSprint(BigDecimal.TEN);
		newStory.setId(storyId);
		long gameId=778;
//		var game=new TsscGame();
//		game.setId(gameId);
//		when(this.gameService.getGame(gameId)).thenReturn(game);
		Assertions.assertThrows(TsscStoryException.class, ()->{
			this.storyService.updateStory(newStory, gameId);
		});
	
	}
	
	
	@Test
	public void updateFailStoryNotFound() throws TsscGameNotFoundException {
		long storyId=445;
		when(this.storyDao.findById(storyId)).thenReturn(null);
		var newStory=new TsscStory();
		newStory.setBusinessValue(BigDecimal.TEN);
		newStory.setPriority(BigDecimal.TEN);
		newStory.setInitialSprint(BigDecimal.TEN);
		newStory.setId(storyId);
		long gameId=778;
		var game=new TsscGame();
		game.setId(gameId);
//		when(this.gameService.getGame(gameId)).thenReturn(game);
		Assertions.assertThrows(TsscStoryNotFound.class, ()->{
			this.storyService.updateStory(newStory, gameId);
		});
	
	}
	
	@Test
	public void updateFailGameNotFound() throws TsscGameNotFoundException {
		long storyId=445;
		var oldStory=new TsscStory();
		oldStory.setId(storyId);
		when(this.storyDao.findById(storyId)).thenReturn(oldStory);
		var newStory=new TsscStory();
		newStory.setBusinessValue(BigDecimal.TEN);
		newStory.setPriority(BigDecimal.TEN);
		newStory.setInitialSprint(BigDecimal.TEN);
		newStory.setId(storyId);
		long gameId=778;
		var game=new TsscGame();
		game.setId(gameId);
		when(this.gameService.getGame(gameId)).thenThrow(new TsscGameNotFoundException(gameId));
		Assertions.assertThrows(TsscGameNotFoundException.class, ()->{
			this.storyService.updateStory(newStory, gameId);
		});
	
	}
	
	@Test
	public void updateFailNull() throws TsscGameNotFoundException {
		long dummyGameId=445;
		Assertions.assertThrows(TsscStoryException.class, ()->{
			this.storyService.updateStory(null, dummyGameId);
		});
	
	}
	
	


}
