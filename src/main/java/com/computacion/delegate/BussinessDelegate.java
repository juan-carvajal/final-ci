package com.computacion.delegate;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.TsscTopic;

@Component
public class BussinessDelegate {
	
	//Games
	
	public Iterable<TsscGame> getAllGames(){
		
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/games")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscGame.class).toIterable();
	}
	
	public void deleteGame(long id) {
		WebClient.create("http://localhost:8080").delete()
        .uri("/api/games/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editGame(TsscGame game) {
		WebClient.create("http://localhost:8080").patch()
        .uri("/api/games/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(game)
        .retrieve().bodyToMono(TsscGame.class).block();
	}
	
	public TsscGame getGame(long id) {
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/games/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscGame.class).block();
	}
	
	public void addGame(TsscGame game) {
		WebClient.create("http://localhost:8080").post()
        .uri("/api/games/add/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(game)
        .retrieve().bodyToMono(TsscGame.class).block();
	}
	
	
	
	
	
	//Topics
	
	public Iterable<TsscTopic> getAllTopics(){
		
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/topics")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscTopic.class).toIterable();
	}
	
	public void deleteTopic(long id) {
		WebClient.create("http://localhost:8080").delete()
        .uri("/api/topics/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editTopic(TsscTopic topic) {
		WebClient.create("http://localhost:8080").patch()
        .uri("/api/topics/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(topic)
        .retrieve().bodyToMono(TsscTopic.class).block();
	}
	
	public TsscTopic getTopic(long id) {
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/topics/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscTopic.class).block();
	}
	
	public void addTopic(TsscTopic topic) {
		WebClient.create("http://localhost:8080").post()
        .uri("/api/topics/add/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(topic)
        .retrieve().bodyToMono(TsscTopic.class).block();
	}
	//Timecontrols
	
	public Iterable<TsscTimecontrol> getAllTimecontrols(){
		
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/timecontrols")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscTimecontrol.class).toIterable();
	}
	
	public void deleteTimecontrol(long id) {
		WebClient.create("http://localhost:8080").delete()
        .uri("/api/timecontrols/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editTimecontrol(TsscTimecontrol timecontrol) {
		WebClient.create("http://localhost:8080").patch()
        .uri("/api/timecontrols/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(timecontrol)
        .retrieve().bodyToMono(TsscTimecontrol.class).block();
	}
	
	public TsscTimecontrol getTimecontrol(long id) {
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/timecontrols/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscTimecontrol.class).block();
	}
	
	public void addTimecontrol(TsscTimecontrol timecontrol) {
		WebClient.create("http://localhost:8080").post()
        .uri("/api/timecontrols/add/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(timecontrol)
        .retrieve().bodyToMono(TsscTimecontrol.class).block();
	}
	
	//Stories
	
public Iterable<TsscStory> getAllStories(){
		
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/stories")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscStory.class).toIterable();
	}
	
	public void deleteStory(long id) {
		WebClient.create("http://localhost:8080").delete()
        .uri("/api/stories/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editStory(TsscStory story) {
		WebClient.create("http://localhost:8080").patch()
        .uri("/api/stories/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(story)
        .retrieve().bodyToMono(TsscStory.class).block();
	}
	
	public TsscStory getStory(long id) {
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/stories/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscStory.class).block();
	}
	
	public void addStory(TsscStory story) {
		WebClient.create("http://localhost:8080").post()
        .uri("/api/stories/add/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(story)
        .retrieve().bodyToMono(TsscStory.class).block();
	}
	
	public Iterable<TsscStory> getStoriesByGame(long id){
		return WebClient.create("http://localhost:8080").get()
		        .uri("api/game/{id}/stories",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscStory.class).toIterable();
	}

}
