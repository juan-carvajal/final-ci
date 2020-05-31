package com.computacion.dao.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.dao.AdminDao;
import com.computacion.dao.GameDao;
import com.computacion.dao.StoryDao;
import com.computacion.dao.TimecontrolDao;
import com.computacion.dao.TopicDao;
import com.computacion.model.TsscAdmin;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.TsscTopic;

@SpringBootTest
//@ContextConfiguration("/applicationContext.xml")
@Rollback(true)
public class GameDaoTest {

	@Autowired
	public GameDao gameDao;

	@Autowired
	public TopicDao topicDao;
	
	
	@Autowired
	public TimecontrolDao tcDao;
	
	@Autowired
	public StoryDao storyDao;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAndGet() {
		TsscTopic topic = new TsscTopic();
		topic.setName("nombre");
		topic.setDescription("hola");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic = topicDao.save(topic);

		TsscGame game1 = new TsscGame();
		game1.setTsscTopic(topic);
		TsscGame gameSaved1 = gameDao.save(game1);

		TsscGame game2 = new TsscGame();
		game2.setTsscTopic(topic);
		TsscGame gameSaved2 = gameDao.save(game2);

		Assertions.assertTrue(gameDao.findByTopicDescription("hola").size() == 2);
		Assertions.assertTrue(gameDao.findByTopicName("nombre").size() == 2);
		Assertions.assertTrue(gameDao.findByTopicId(topic.getId()).size() == 2);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {

		TsscGame game1 = new TsscGame();
		game1 = gameDao.save(game1);

		game1.setAdminPassword("pass");
		gameDao.update(game1);

		Assertions.assertTrue(game1.getAdminPassword().equals("pass"));

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testGameExtra() {
		
		TsscTimecontrol tc=new TsscTimecontrol();
		tc=tcDao.save(tc);

		
		TsscGame game1 = new TsscGame();
		game1.setScheduledDate(LocalDate.of(2010, 03, 3));
		game1.setTsscStories(new ArrayList<>());
		game1.setTsscTimecontrol(new ArrayList<>());
		game1.addTsscTimecontrol(tc);
		game1=gameDao.save(game1);
		
		tc.setTsscGame(game1);
		tc=tcDao.update(tc);
		
		for (int i = 0; i < 6; i++) {
			TsscStory story=new TsscStory();
			story.setBusinessValue(BigDecimal.TEN);
			story.setPriority(BigDecimal.TEN);
			story.setInitialSprint(BigDecimal.TEN);
			story.setTsscGame(game1);
			game1.addTsscStory(story);
			storyDao.save(story);
			gameDao.save(game1);
		}
		
		
		TsscGame game2 = new TsscGame();
		game2.setScheduledDate(LocalDate.of(2010, 03, 3));
		game2.setTsscStories(new ArrayList<>());
		game2=gameDao.save(game2);

		List<TsscGame>queryResult=gameDao.gamesExtraQuery(LocalDate.of(2010, 03, 3));
		Assertions.assertTrue(queryResult.size()==2);
		Assertions.assertTrue(queryResult.get(0).getId()==game1.getId());
		Assertions.assertTrue(queryResult.get(1).getId()==game2.getId());

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testGetByDateAndTime() {

		TsscGame game1 = new TsscGame();
		game1.setScheduledDate(LocalDate.of(2019, 04, 5));
		game1.setScheduledTime(LocalTime.of(13, 15));
		game1 = gameDao.save(game1);
		
		
		TsscGame game2 = new TsscGame();
		game2.setScheduledDate(LocalDate.of(2019, 04, 1));
		game2.setScheduledTime(LocalTime.of(7, 15));
		game2 = gameDao.save(game2);
		
		TsscGame game3 = new TsscGame();
		game3.setScheduledDate(LocalDate.of(2019, 04, 1));
		game3.setScheduledTime(LocalTime.of(8,15));
		game3 = gameDao.save(game3);

		
		List<TsscGame> query1=gameDao.findByDateAndTimeRange(LocalDate.of(2019, 04, 1), LocalTime.of(6, 15), LocalTime.of(9,15));

		Assertions.assertTrue(query1.size()==2);
		
		List<TsscGame> query2=gameDao.findByDateRange(LocalDate.of(2019, 04, 1), LocalDate.of(2019, 04, 6));

		Assertions.assertTrue(query2.size()==3);

	}

}
