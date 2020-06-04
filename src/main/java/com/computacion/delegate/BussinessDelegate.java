package com.computacion.delegate;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.computacion.model.TsscGame;
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
	//Stories

}
