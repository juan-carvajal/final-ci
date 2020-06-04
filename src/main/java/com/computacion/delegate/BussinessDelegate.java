package com.computacion.delegate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	// Games

	private WebClient wc;
	
	
	
	public BussinessDelegate() {
		this.wc=WebClient.create("http://localhost:8080/api");
		//this.wc=wc.mutate().baseUrl("http://localhost:8080/api").build();
		
	}

	public Iterable<TsscGame> getAllGames() {
		return wc.get().uri("/games").accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToFlux(TsscGame.class).toIterable();
	}

	public void deleteGame(long id) {
		wc.delete().uri("/games/del/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Void.class).block();
	}

	public void editGame(TsscGame game) {
		wc.patch().uri("/games/edit/").accept(MediaType.APPLICATION_JSON)
				.bodyValue(game).retrieve().bodyToMono(TsscGame.class).block();
	}

	public TsscGame getGame(long id) {
		return wc.get().uri("/games/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(TsscGame.class).block();
	}

	public void addGame(TsscGame game) {
		wc.post().uri("/games/add/").accept(MediaType.APPLICATION_JSON)
				.bodyValue(game).retrieve().bodyToMono(TsscGame.class).block();
	}
	
	
	public Iterable<TsscGame> gamesExtraQuery(LocalDate date){
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
		return  wc.get().uri("/games/byDate/{date}", date)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(TsscGame.class).toIterable();
	}

	// Topics

	public Iterable<TsscTopic> getAllTopics() {

		return wc.get().uri("/topics").accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToFlux(TsscTopic.class).toIterable();
	}

	public void deleteTopic(long id) {
		wc.delete().uri("/topics/del/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Void.class).block();
	}

	public void editTopic(TsscTopic topic) {
		wc.patch().uri("/topics/edit/").accept(MediaType.APPLICATION_JSON)
				.bodyValue(topic).retrieve().bodyToMono(TsscTopic.class).block();
	}

	public TsscTopic getTopic(long id) {
		return wc.get().uri("/topics/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(TsscTopic.class).block();
	}

	public void addTopic(TsscTopic topic) {
		wc.post().uri("/topics/add/").accept(MediaType.APPLICATION_JSON)
				.bodyValue(topic).retrieve().bodyToMono(TsscTopic.class).block();
	}
	
	
	public Iterable<TsscTopic> getTopicsByDate(LocalDate date){
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
		return  wc.get().uri("/topics/byDate/{date}", date)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(TsscTopic.class).toIterable();
	}
	// Timecontrols

	public Iterable<TsscTimecontrol> getAllTimecontrols() {

		return wc.get().uri("/timecontrols")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(TsscTimecontrol.class).toIterable();
	}

	public void deleteTimecontrol(long id) {
		wc.delete().uri("/timecontrols/del/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Void.class).block();
	}

	public void editTimecontrol(TsscTimecontrol timecontrol) {
		wc.patch().uri("/timecontrols/edit/")
				.accept(MediaType.APPLICATION_JSON).bodyValue(timecontrol).retrieve().bodyToMono(TsscTimecontrol.class)
				.block();
	}

	public TsscTimecontrol getTimecontrol(long id) {
		return wc.get().uri("/timecontrols/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(TsscTimecontrol.class).block();
	}

	public void addTimecontrol(TsscTimecontrol timecontrol) {
		wc.post().uri("/timecontrols/add/")
				.accept(MediaType.APPLICATION_JSON).bodyValue(timecontrol).retrieve().bodyToMono(TsscTimecontrol.class)
				.block();
	}

	// Stories

	public Iterable<TsscStory> getAllStories() {

		return wc.get().uri("/stories").accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToFlux(TsscStory.class).toIterable();
	}

	public void deleteStory(long id) {
		wc.delete().uri("/stories/del/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Void.class).block();
	}

	public void editStory(TsscStory story) {
		wc.patch().uri("/stories/edit/").accept(MediaType.APPLICATION_JSON)
				.bodyValue(story).retrieve().bodyToMono(TsscStory.class).block();
	}

	public TsscStory getStory(long id) {
		return wc.get().uri("/stories/{id}", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(TsscStory.class).block();
	}

	public void addStory(TsscStory story) {
		wc.post().uri("/stories/add/").accept(MediaType.APPLICATION_JSON)
				.bodyValue(story).retrieve().bodyToMono(TsscStory.class).block();
	}

	public void addStoryWithGame(TsscStory story, long id) {
		story.setId(0);
		wc.post().uri("/games/{id}/stories/add", id)
				.accept(MediaType.APPLICATION_JSON).bodyValue(story).retrieve().bodyToMono(TsscStory.class).block();
	}

	public Iterable<TsscStory> getStoriesByGame(long id) {
		return wc.get().uri("/game/{id}/stories", id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(TsscStory.class).toIterable();
	}

}
