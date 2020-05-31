package com.computacion.integration;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscTopicService;
import com.computacion.service.TsscTopicServiceImpl;

@SpringBootTest
@Rollback(true)
public class TsscTopicServiceTests {

	
	private TsscTopicService topicService;
	private long topicId;
	
	@Autowired
	public TsscTopicServiceTests(TsscTopicService topicService) {
		this.topicService=topicService;
	}
	
	@BeforeEach
	public void setUp() throws TsscTopicException {
		TsscTopic t=new TsscTopic();
		t.setDefaultGroups(1);;
		t.setDefaultSprints(1);
		this.topicId=this.topicService.createTopic(t).getId();
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testGetTopic() throws TsscTopicNotFoundException {

		
		Assertions.assertTrue(this.topicService.getTopic(this.topicId).getId()==this.topicId);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreateFail() throws TsscTopicException {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(0);
		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			this.topicService.createTopic(topic);
		});


	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreateNull() {
		TsscTopic topic=null;
		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			this.topicService.createTopic(topic);
		});
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreateGood() throws TsscTopicException {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		var created=this.topicService.createTopic(topic);
		Assertions.assertTrue(created!=null);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateGood() throws TsscTopicException, TsscTopicNotFoundException {
		var newTopic=this.topicService.getTopic(this.topicId);
		newTopic.setDefaultGroups(4);
		newTopic.setDefaultSprints(4);
		var updated=this.topicService.updateTopic(newTopic);
		Assertions.assertTrue(updated.getId()==this.topicId);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateFail() throws TsscTopicException, TsscTopicNotFoundException {
		var newTopic=new TsscTopic();
		newTopic.setId(this.topicId);
		newTopic.setDefaultGroups(0);
		newTopic.setDefaultSprints(4);
		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			this.topicService.updateTopic(newTopic);
		});
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateNotFound() {
		var topic=new TsscTopic();
		topic.setId(48855);
		Assertions.assertThrows(TsscTopicNotFoundException.class, ()->{
			
			this.topicService.updateTopic(topic);
		});

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateNull() {

		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			this.topicService.updateTopic(null);
		});

	}
	
	
	
	
	
}
