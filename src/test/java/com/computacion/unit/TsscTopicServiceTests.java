package com.computacion.unit;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.computacion.dao.TopicDao;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscTopicServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TsscTopicServiceTests {

	
	private static TsscTopicServiceImpl service;
	@Mock
	private static TopicDao dao;
	private long testID=64;
	private TsscTopic mockTopic=new TsscTopic();
	
	
	@BeforeEach
	public void setUp() {
		TsscTopicServiceTests.service=new TsscTopicServiceImpl(TsscTopicServiceTests.dao);
		mockTopic.setId(testID);
		
	}
	
	@Test
	public void testGetTopic() throws TsscTopicNotFoundException {
		when(dao.findById(testID)).thenReturn(mockTopic);
		
		Assertions.assertTrue(service.getTopic(testID).getId()==testID);
	}
	
	
	@Test
	public void testCreateFail() throws TsscTopicException {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(0);
		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			service.createTopic(topic);
		});
		verifyNoInteractions(dao);


	}
	
	@Test
	public void testCreateNull() {
		TsscTopic topic=null;
		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			service.createTopic(topic);
		});
	}
	
	@Test
	public void testCreateGood() throws TsscTopicException {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		service.createTopic(topic);
		verify(dao).save(topic);
	}
	
	
	@Test
	public void testUpdateGood() throws TsscTopicException, TsscTopicNotFoundException {
		when(dao.findById(testID)).thenReturn(mockTopic);
		mockTopic.setDefaultGroups(4);
		mockTopic.setDefaultSprints(4);
		service.updateTopic(mockTopic);
		verify(dao).update(mockTopic);
	}
	
	@Test
	public void testUpdateFail() throws TsscTopicException, TsscTopicNotFoundException {
		when(dao.findById(testID)).thenReturn(mockTopic);
		mockTopic.setDefaultGroups(0);
		mockTopic.setDefaultSprints(0);
		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			service.updateTopic(mockTopic);
		});
	}
	
	@Test
	public void testUpdateNotFound() {
		var topic=new TsscTopic();
		topic.setId(48855);
		Assertions.assertThrows(TsscTopicNotFoundException.class, ()->{
			
			service.updateTopic(topic);
		});

	}
	
	@Test
	public void testUpdateNull() {

		Assertions.assertThrows(TsscTopicException.class, ()->{
			
			service.updateTopic(null);
		});

	}
	
	
	
	
	
}
