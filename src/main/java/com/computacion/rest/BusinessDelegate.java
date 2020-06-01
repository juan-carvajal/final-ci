package com.computacion.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.computacion.model.TsscGame;
import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class BusinessDelegate {
	
	

	
	public void getAllGames() {
		var cosa=WebClient.create("http://localhost:8080").get()
        .uri("/games")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new TypeReference<Iterable<TsscGame>>(){}.getClass());
		
		
		

	}

}
