package com.computacion.integration;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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


@SpringBootTest
@Rollback(true)
public class TsscStoryServiceTests {



	private TsscGameService gameService;
	
	
	private TsscStoryService storyService;
	
	private long storyId;
	private long gameId;
	
	@Autowired
	public TsscStoryServiceTests(TsscGameService gameService,TsscStoryService storyService) {
		this.gameService=gameService;
		this.storyService=storyService;
	}

	@BeforeEach
	public void setup() throws TsscGameNotFoundException, TsscStoryException, TsscGameException {
		TsscGame g=new TsscGame();
		g.setNGroups(4);
		g.setNSprints(4);
		this.gameId=this.gameService.createGame(g).getId();
		TsscStory s=new TsscStory();
		s.setBusinessValue(BigDecimal.TEN);
		s.setInitialSprint(BigDecimal.ONE)
		;s.setPriority(BigDecimal.TEN);
		this.storyId=this.storyService.createStory(s, this.gameId).getId();

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testGet() throws TsscStoryNotFound {



		var s=this.storyService.getStory(this.storyId);
		Assertions.assertTrue(s.getId()==this.storyId);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createFailConstraints() throws TsscGameNotFoundException, TsscStoryException {

		var mockStory=new TsscStory();

		mockStory.setBusinessValue(BigDecimal.ZERO);
		mockStory.setPriority(BigDecimal.ZERO);
		mockStory.setInitialSprint(BigDecimal.ZERO);

		Assertions.assertThrows(TsscStoryException.class, ()->{
			
			this.storyService.createStory(mockStory, this.gameId);
		});



	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createPass() throws TsscGameNotFoundException, TsscStoryException {

		var mockStory=new TsscStory();
		mockStory.setBusinessValue(BigDecimal.TEN);
		mockStory.setPriority(BigDecimal.TEN);
		mockStory.setInitialSprint(BigDecimal.TEN);

		var result=this.storyService.createStory(mockStory, this.gameId);

		Assertions.assertTrue(result!=null);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createFailGameNotFound() throws TsscGameNotFoundException {
		var mockStory=new TsscStory();
		mockStory.setBusinessValue(BigDecimal.TEN);
		mockStory.setPriority(BigDecimal.TEN);
		mockStory.setInitialSprint(BigDecimal.TEN);
		long mockGameID=447;

		Assertions.assertThrows(TsscGameNotFoundException.class, ()->{
			
			this.storyService.createStory(mockStory, mockGameID);
		});
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createFailNull() {
Assertions.assertThrows(TsscStoryException.class, ()->{
			long dummy=154;
			this.storyService.createStory(null, dummy);
		});
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFailContraints() throws TsscGameNotFoundException {

		var newStory=new TsscStory();
		newStory.setBusinessValue(BigDecimal.TEN);
		newStory.setPriority(BigDecimal.ZERO);
		newStory.setInitialSprint(BigDecimal.TEN);
		newStory.setId(storyId);
		Assertions.assertThrows(TsscStoryException.class, ()->{
			this.storyService.updateStory(newStory, gameId);
		});
	
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFailStoryNotFound() throws TsscGameNotFoundException {
		long storyId=445;
		var newStory=new TsscStory();
		newStory.setBusinessValue(BigDecimal.TEN);
		newStory.setPriority(BigDecimal.TEN);
		newStory.setInitialSprint(BigDecimal.TEN);
		newStory.setId(storyId);
		Assertions.assertThrows(TsscStoryNotFound.class, ()->{
			this.storyService.updateStory(newStory, gameId);
		});
	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFailGameNotFound() throws TsscGameNotFoundException {
		var oldStory=new TsscStory();
		oldStory.setId(storyId);
		var newStory=new TsscStory();
		newStory.setBusinessValue(BigDecimal.TEN);
		newStory.setPriority(BigDecimal.TEN);
		newStory.setInitialSprint(BigDecimal.TEN);
		newStory.setId(storyId);
		long gameId=778;
		Assertions.assertThrows(TsscGameNotFoundException.class, ()->{
			this.storyService.updateStory(newStory, gameId);
		});
	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFailNull() throws TsscGameNotFoundException {
		long dummyGameId=445;
		Assertions.assertThrows(TsscStoryException.class, ()->{
			this.storyService.updateStory(null, dummyGameId);
		});
	
	}
	
	


}
