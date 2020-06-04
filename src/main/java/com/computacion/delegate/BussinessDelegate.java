package com.computacion.delegate;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.computacion.model.TsscGame;

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
	
	public Iterable<TsscGame> getAllTopics(){
		
		return WebClient.create("http://localhost:8080").get()
		        .uri("/api/topics")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscGame.class).toIterable();
	}
	//Timecontrols
	//Stories

}
