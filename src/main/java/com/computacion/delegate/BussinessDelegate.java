package com.computacion.delegate;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public String url="http://localhost:8080";
	public WebClient wc;
	
	public BussinessDelegate() {
		wc=WebClient.create();
	}
	
	
	public Iterable<TsscGame> getAllGames(){
		return wc.get()
		        .uri(url+"/api/games")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscGame.class).toIterable();
	}
	
	public void deleteGame(long id) {
		wc.delete()
        .uri(url+"/api/games/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editGame(TsscGame game) {
		wc.patch()
        .uri(url+"/api/games/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(game)
        .retrieve().bodyToMono(TsscGame.class).block();
	}
	
	public TsscGame getGame(long id) {
		return wc.get()
		        .uri(url+"/api/games/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscGame.class).block();
	}
	
	public void addGame(TsscGame game) {
		wc.post()
        .uri(url+"/api/games/add/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(game)
        .retrieve().bodyToMono(TsscGame.class).block();
	}
	

	
	
	//Topics
	
	public Iterable<TsscTopic> getAllTopics(){
		
		return wc.get()
		        .uri(url+"/api/topics")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscTopic.class).toIterable();
	}
	
	public void deleteTopic(long id) {
		wc.delete()
        .uri(url+"/api/topics/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editTopic(TsscTopic topic) {
		wc.patch()
        .uri(url+"/api/topics/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(topic)
        .retrieve().bodyToMono(TsscTopic.class).block();
	}
	
	public TsscTopic getTopic(long id) {
		return wc.get()
		        .uri(url+"/api/topics/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscTopic.class).block();
	}
	
	public void addTopic(TsscTopic topic) {
		wc.post()
        .uri(url+"/api/topics/add/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(topic)
        .retrieve().bodyToMono(TsscTopic.class).block();
	}
	//Timecontrols
	
	public Iterable<TsscTimecontrol> getAllTimecontrols(){
		
		return wc.get()
		        .uri(url+"/api/timecontrols")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscTimecontrol.class).toIterable();
	}
	
	public void deleteTimecontrol(long id) {
		wc.delete()
        .uri(url+"/api/timecontrols/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editTimecontrol(TsscTimecontrol timecontrol) {
		wc.patch()
        .uri(url+"/api/timecontrols/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(timecontrol)
        .retrieve().bodyToMono(TsscTimecontrol.class).block();
	}
	
	public TsscTimecontrol getTimecontrol(long id) {
		return wc.get()
		        .uri(url+"/api/timecontrols/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscTimecontrol.class).block();
	}
	
	public void addTimecontrol(TsscTimecontrol timecontrol) {
		try {
			wc.post()
	        .uri(url+"/api/timecontrols/add/")
	        .accept(MediaType.APPLICATION_JSON).bodyValue(timecontrol)
	        .retrieve().bodyToMono(TsscTimecontrol.class).block();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//Stories
	
public Iterable<TsscStory> getAllStories(){
		
		return wc.get()
		        .uri(url+"/api/stories")
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscStory.class).toIterable();
	}
	
	public void deleteStory(long id) {
		wc.delete()
        .uri(url+"/api/stories/del/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(Void.class).block();
	}
	
	public void editStory(TsscStory story) {
		wc.patch()
        .uri(url+"/api/stories/edit/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(story)
        .retrieve().bodyToMono(TsscStory.class).block();
	}
	
	public TsscStory getStory(long id) {
		return wc.get()
		        .uri(url+"/api/stories/{id}",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToMono(TsscStory.class).block();
	}
	
	public void addStory(TsscStory story) {
		wc.post()
        .uri(url+"/api/stories/add/")
        .accept(MediaType.APPLICATION_JSON).bodyValue(story)
        .retrieve().bodyToMono(TsscStory.class).block();
	}
	
	public void addStoryWithGame(TsscStory story,long id) {
																																															story.setId(0);
		wc.post()
        .uri(url+"api/games/{id}/stories/add",id)
        .accept(MediaType.APPLICATION_JSON).bodyValue(story)
        .retrieve().bodyToMono(TsscStory.class).block();
	}
	
	public Iterable<TsscStory> getStoriesByGame(long id){
		return wc.get()
		        .uri(url+"api/game/{id}/stories",id)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve().bodyToFlux(TsscStory.class).toIterable();
	}
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	
	public Iterable<TsscTopic> getTopicsByDate(LocalDate date){
		 return wc.get()
	        .uri(url+"api/topics/byDate/{date}",date)
	        .accept(MediaType.APPLICATION_JSON)
	        .retrieve().bodyToFlux(TsscTopic.class).toIterable();
	}
	
	
	public Iterable<TsscGame> gamesExtraQuery(LocalDate date){
		 return wc.get()
	        .uri(url+"api/games/byDate/{date}",date)
	        .accept(MediaType.APPLICATION_JSON)
	        .retrieve().bodyToFlux(TsscGame.class).toIterable();
	}


}
