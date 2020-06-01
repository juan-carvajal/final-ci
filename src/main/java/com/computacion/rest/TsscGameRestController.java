package com.computacion.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.model.TsscGame;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscGameService;

@RestController
public class TsscGameRestController {
	
	@Autowired
	public TsscGameService gameService;

	@Autowired
	public TsscGameRepository gameRepo;

	@Autowired
	public TsscTopicRepository topicRepo;
	
	@GetMapping("api/games")
	public Iterable<TsscGame> getAllGames(){
		return gameRepo.findAll();
	}
	
	@GetMapping("/api/games/{id}")
	public TsscGame getGameById(@PathVariable long id) {
		try {
			return this.gameService.getGame(id);
		} catch (TsscGameNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	@PatchMapping("/api/games/edit")
	public TsscGame EditGame(@RequestBody TsscGame game) throws TsscTopicNotFoundException, TsscGameException, TsscGameNotFoundException {
		if (game.getTsscTopic() != null) {
			return this.gameService.updateGame(game, game.getTsscTopic().getId());

		} else {
			return this.gameService.updateGame(game);

		}
	}
	
	
	@PostMapping("/api/games/add")
	public TsscGame AddGame(@RequestBody TsscGame game) throws TsscTopicNotFoundException, TsscGameException {
		if (game.getTsscTopic() != null) {
			return this.gameService.createGame(game, game.getTsscTopic().getId());
		} else {
			return this.gameService.createGame(game);
		}
	}
	
	@DeleteMapping("/api/games/del")
	public void DeleteGame(@PathVariable long id) {
		this.gameRepo.deleteById(id);
	}
	
	
	
	
	

}
