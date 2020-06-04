package com.computacion.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.TsscTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DelegateTest {
	
	public BussinessDelegate delegate;
	
	public static MockWebServer mockBackEnd;
	public ObjectMapper mapper=new ObjectMapper();
	 
    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }
 
    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }
    
    @BeforeEach
    void initialize() {
        String baseUrl = "http://localhost:"+mockBackEnd.getPort();
        delegate = new BussinessDelegate();
        delegate.setUrl(baseUrl);
    }
	
	@Test
	public void testGetAllGame() throws JsonProcessingException, InterruptedException {
		
		TsscGame game1=new TsscGame();
		game1.setId(1);
		TsscGame game2=new TsscGame();
		game2.setId(2);
		
		ArrayList<TsscGame> lista=new ArrayList<>();
		lista.add(game1);
		lista.add(game2);
		Iterable<TsscGame> iterable=lista;
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(iterable))
				.addHeader("Content-Type", "application/json"));
		
		
		var delegates=delegate.getAllGames();
		List<TsscGame> result = new ArrayList<TsscGame>();
	    delegates.forEach(result::add);
	    System.out.println(result.size());
		
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/games", recordedRequest.getPath());
		Assertions.assertEquals(result.size(), lista.size());
		
	}
	
	@Test
	public void testDeleteGame() throws JsonProcessingException, InterruptedException {
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(Mono.just(Void.class)))
				.addHeader("Content-Type", "application/json"));
		
		delegate.deleteGame(100);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("DELETE", recordedRequest.getMethod());
		assertEquals("/api/games/del/100", recordedRequest.getPath());
		
	}
	
	@Test
	public void testEditGame() throws JsonProcessingException, InterruptedException {
		
		TsscGame game1=new TsscGame();
		game1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(game1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.editGame(game1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("PATCH", recordedRequest.getMethod());
		assertEquals("/api/games/edit/", recordedRequest.getPath());
		
	}
	
	@Test
	public void testGetGame() throws JsonProcessingException, InterruptedException {
		
		TsscGame game1=new TsscGame();
		game1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(game1))
				.addHeader("Content-Type", "application/json"));
		
		var game= delegate.getGame(game1.getId());
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/games/1", recordedRequest.getPath());
		
	}
	
	@Test
	public void testAddGame() throws JsonProcessingException, InterruptedException {
		
		TsscGame game1=new TsscGame();
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(game1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.addGame(game1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("POST", recordedRequest.getMethod());
		assertEquals("/api/games/add/", recordedRequest.getPath());
		
	}
	
	@Test
	public void testGetAllTopic() throws JsonProcessingException, InterruptedException {
		
		TsscTopic topic1=new TsscTopic();
		topic1.setId(1);
		TsscTopic topic2=new TsscTopic();
		topic2.setId(2);
		
		ArrayList<TsscTopic> lista=new ArrayList<>();
		lista.add(topic1);
		lista.add(topic2);
		Iterable<TsscTopic> iterable=lista;
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(iterable))
				.addHeader("Content-Type", "application/json"));
		
		
		var delegates=delegate.getAllTopics();
		List<TsscTopic> result = new ArrayList<TsscTopic>();
	    delegates.forEach(result::add);
	    System.out.println(result.size());
		
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/topics", recordedRequest.getPath());
		Assertions.assertEquals(result.size(), lista.size());
		
	}
	
	@Test
	public void testDeleteTopic() throws JsonProcessingException, InterruptedException {
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(Mono.just(Void.class)))
				.addHeader("Content-Type", "application/json"));
		
		delegate.deleteTopic(100);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("DELETE", recordedRequest.getMethod());
		assertEquals("/api/topics/del/100", recordedRequest.getPath());
		
	}
	
	@Test
	public void testEditTopic() throws JsonProcessingException, InterruptedException {
		
		TsscTopic topic1=new TsscTopic();
		topic1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(topic1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.editTopic(topic1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("PATCH", recordedRequest.getMethod());
		assertEquals("/api/topics/edit/", recordedRequest.getPath());
		
	}
	
	@Test
	public void testGetTopic() throws JsonProcessingException, InterruptedException {
		
		TsscTopic topic1=new TsscTopic();
		topic1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(topic1))
				.addHeader("Content-Type", "application/json"));
		
		var topic= delegate.getTopic(topic1.getId());
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/topics/1", recordedRequest.getPath());
		
	}
	
	@Test
	public void testAddTopic() throws JsonProcessingException, InterruptedException {
		
		TsscTopic topic1=new TsscTopic();
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(topic1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.addTopic(topic1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("POST", recordedRequest.getMethod());
		assertEquals("/api/topics/add/", recordedRequest.getPath());
		
	}
	
	@Test
	public void testGetAllTimecontrol() throws JsonProcessingException, InterruptedException {
		
		TsscTimecontrol timecontrol1=new TsscTimecontrol();
		timecontrol1.setId(1);
		TsscTimecontrol timecontrol2=new TsscTimecontrol();
		timecontrol2.setId(2);
		
		ArrayList<TsscTimecontrol> lista=new ArrayList<>();
		lista.add(timecontrol1);
		lista.add(timecontrol2);
		Iterable<TsscTimecontrol> iterable=lista;
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(iterable))
				.addHeader("Content-Type", "application/json"));
		
		
		var delegates=delegate.getAllTimecontrols();
		List<TsscTimecontrol> result = new ArrayList<TsscTimecontrol>();
	    delegates.forEach(result::add);
	    System.out.println(result.size());
		
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/timecontrols", recordedRequest.getPath());
		Assertions.assertEquals(result.size(), lista.size());
		
	}
	
	@Test
	public void testDeleteTimecontrol() throws JsonProcessingException, InterruptedException {
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(Mono.just(Void.class)))
				.addHeader("Content-Type", "application/json"));
		
		delegate.deleteTimecontrol(100);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("DELETE", recordedRequest.getMethod());
		assertEquals("/api/timecontrols/del/100", recordedRequest.getPath());
		
	}
	
	@Test
	public void testEditTimecontrol() throws JsonProcessingException, InterruptedException {
		
		TsscTimecontrol timecontrol1=new TsscTimecontrol();
		timecontrol1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(timecontrol1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.editTimecontrol(timecontrol1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("PATCH", recordedRequest.getMethod());
		assertEquals("/api/timecontrols/edit/", recordedRequest.getPath());
		
	}
	
	@Test
	public void testGetTimecontrol() throws JsonProcessingException, InterruptedException {
		
		TsscTimecontrol timecontrol1=new TsscTimecontrol();
		timecontrol1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(timecontrol1))
				.addHeader("Content-Type", "application/json"));
		
		var timecontrol= delegate.getTimecontrol(timecontrol1.getId());
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/timecontrols/1", recordedRequest.getPath());
		
	}
	
	@Test
	public void testAddTimecontrol() throws JsonProcessingException, InterruptedException {
		
		TsscTimecontrol timecontrol1=new TsscTimecontrol();
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(timecontrol1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.addTimecontrol(timecontrol1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("POST", recordedRequest.getMethod());
		assertEquals("/api/timecontrols/add/", recordedRequest.getPath());
		
	}
	
	@Test
	public void testGetAllStory() throws JsonProcessingException, InterruptedException {
		
		TsscStory story1=new TsscStory();
		story1.setId(1);
		TsscStory story2=new TsscStory();
		story2.setId(2);
		
		ArrayList<TsscStory> lista=new ArrayList<>();
		lista.add(story1);
		lista.add(story2);
		Iterable<TsscStory> iterable=lista;
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(iterable))
				.addHeader("Content-Type", "application/json"));
		
		
		var delegates=delegate.getAllStories();
		List<TsscStory> result = new ArrayList<TsscStory>();
	    delegates.forEach(result::add);
	    System.out.println(result.size());
		
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/stories", recordedRequest.getPath());
		Assertions.assertEquals(result.size(), lista.size());
		
	}
	
	@Test
	public void testDeleteStory() throws JsonProcessingException, InterruptedException {
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(Mono.just(Void.class)))
				.addHeader("Content-Type", "application/json"));
		
		delegate.deleteStory(100);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		System.out.println("Aqui");
		assertEquals("DELETE", recordedRequest.getMethod());
		assertEquals("/api/stories/del/100", recordedRequest.getPath());
		
	}
	
	@Test
	public void testEditStory() throws JsonProcessingException, InterruptedException {
		
		TsscStory story1=new TsscStory();
		story1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(story1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.editStory(story1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("PATCH", recordedRequest.getMethod());
		assertEquals("/api/stories/edit/", recordedRequest.getPath());
		
	}
	
	@Test
	public void testGetStory() throws JsonProcessingException, InterruptedException {
		
		TsscStory story1=new TsscStory();
		story1.setId(1);
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(story1))
				.addHeader("Content-Type", "application/json"));
		
		var story= delegate.getStory(story1.getId());
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/api/stories/1", recordedRequest.getPath());
		
	}
	
	@Test
	public void testAddStory() throws JsonProcessingException, InterruptedException {
		
		TsscStory story1=new TsscStory();
		
		mockBackEnd.enqueue(new MockResponse()
				.setBody(mapper.writeValueAsString(story1))
				.addHeader("Content-Type", "application/json"));
		
		delegate.addStory(story1);
		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("POST", recordedRequest.getMethod());
		assertEquals("/api/stories/add/", recordedRequest.getPath());
		
	}
	
	

}
