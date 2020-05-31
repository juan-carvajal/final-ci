package com.computacion.dao.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.dao.StoryDao;
import com.computacion.dao.TimecontrolDao;
import com.computacion.model.TsscAdmin;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTimecontrol;

@SpringBootTest
//@ContextConfiguration("/applicationContext.xml")
@Rollback(true)
public class StoryDaoTest {
	
	@Autowired
	public StoryDao storyDao;
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAndGet() {
		TsscStory story= new TsscStory();
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		story.setInitialSprint(BigDecimal.TEN);
		story=storyDao.save(story);
		
		
		Assertions.assertTrue(story.getId()==storyDao.findById(story.getId()).getId());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {
		TsscStory story= new TsscStory();
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		story.setInitialSprint(BigDecimal.TEN);
		story=storyDao.save(story);
		
		story.setAltDescripton("alt");
		story=storyDao.update(story);
		Assertions.assertTrue(storyDao.findById(story.getId()).getAltDescripton().equals("alt"));
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		TsscStory story= new TsscStory();
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		story.setInitialSprint(BigDecimal.TEN);
		story=storyDao.save(story);
		
		storyDao.delete(story);
		Assertions.assertTrue(storyDao.findById(story.getId())==null);

	}

}
