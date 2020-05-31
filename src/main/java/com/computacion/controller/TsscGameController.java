package com.computacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.computacion.model.TsscGame;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscGameService;

@Controller
public class TsscGameController {

	@Autowired
	public TsscGameService gameService;

	@Autowired
	public TsscGameRepository gameRepo;

	@Autowired
	public TsscTopicRepository topicRepo;

	@GetMapping("/games")
	public String viewGames(Model model) {
		model.addAttribute("games", gameRepo.findAll());
		return "juegos/ver";
	}

	@GetMapping("/games/add")
	public String addGame(Model model) {
		model.addAttribute("game", new TsscGame());
		model.addAttribute("topics", topicRepo.findAll());
		return "juegos/add";
	}

	@GetMapping("/games/del/{id}")
	public String deleteGame(@PathVariable long id) {
		try {
			this.gameRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/games";
	}
	
	

	@PostMapping("/games/edit/{id}")
	public String editGame(@PathVariable long id, @Validated @ModelAttribute("game") TsscGame game,
			BindingResult bindingResult, Model model) {

		try {
			if (bindingResult.hasErrors()) {
				//model.addAttribute("game", this.gameService.getGame(id));
				model.addAttribute("topics", topicRepo.findAll());
				return "juegos/edit";
			}
			if (game.getTsscTopic() != null) {
				this.gameService.updateGame(game, game.getTsscTopic().getId());
			} else {
				this.gameService.updateGame(game);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/games";
	}

	@GetMapping("/games/edit/{id}")
	public String editGame(@PathVariable long id, Model model) {
		try {
			TsscGame game = gameService.getGame(id);
			model.addAttribute("game", game);
			model.addAttribute("topics", topicRepo.findAll());
			return "/juegos/edit";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/games";
		}
	}

	@PostMapping("/games/add")
	public String addGamePost(@Validated @ModelAttribute("game") TsscGame game, BindingResult bindingResult,
			Model model) throws TsscTopicNotFoundException, TsscGameException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("topics", topicRepo.findAll());
			return "/juegos/add";
		} else {
			if (game.getTsscTopic() != null) {
				this.gameService.createGame(game, game.getTsscTopic().getId());
			} else {
				this.gameService.createGame(game);
			}
		}

		return "redirect:/games";
	}

}
