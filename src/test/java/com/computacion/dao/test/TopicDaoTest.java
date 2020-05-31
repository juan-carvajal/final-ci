package com.computacion.dao.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.dao.GameDao;
import com.computacion.dao.TopicDao;
import com.computacion.model.TsscAdmin;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscTopic;

@SpringBootTest
//@ContextConfiguration("/applicationContext.xml")
@Rollback(true)
public class TopicDaoTest {

	
	@Autowired
	public TopicDao topicDao;
	
	@Autowired
	public GameDao gameDao;
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAndGet() {
		TsscTopic topic=new TsscTopic();
		topic.setName("nombre");
		topic.setDescription("desc");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topicDao.save(topic);
		
		
		Assertions.assertTrue(topicDao.findByName("nombre").getId()==topic.getId());
		Assertions.assertTrue(topicDao.findbyDescription("desc").getId()==topic.getId());
	}
	
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		TsscTopic topic=new TsscTopic();
		topic.setName("nombre");
		topic.setDescription("desc");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topicDao.save(topic);
		
		topic.setDefaultGroups(10);
		topic=topicDao.update(topic);
		
		
		Assertions.assertTrue(topicDao.findByName("nombre").getDefaultGroups()==10);

	}
	
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		TsscTopic topic=new TsscTopic();
		topic.setName("nombre");
		topic.setDescription("desc");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topicDao.save(topic);
		
		topicDao.delete(topic);
		
		


		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {

			topicDao.findByName("nombre");
		});

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testTemasExtra() {
		
		TsscTopic topic=new TsscTopic();
		topic.setName("nombre");
		topic.setDescription("desc");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topicDao.save(topic);
		
		
		for (int i = 0; i < 50; i++) {
			TsscGame game=new TsscGame();
			game.setTsscTopic(topic);
			game.setScheduledDate(LocalDate.of(2019, 04, 24));
			gameDao.save(game);
		}
		

		var queryResult=topicDao.listTopicWithGamesCountByDate(LocalDate.of(2019, 04, 24));
		Assertions.assertTrue(queryResult.size()==1);
		Assertions.assertTrue(((TsscTopic)queryResult.get(0)[0]).getId()==topic.getId());
		Assertions.assertTrue(((Long)queryResult.get(0)[1]).longValue()==50);

	}
}
