package com.computacion.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.computacion.delegate.BussinessDelegate;
import com.computacion.model.TsscGame;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscGameService;
import com.fasterxml.jackson.core.type.TypeReference;

import reactor.core.publisher.Mono;

@Controller
public class TsscGameController {
	
	@Autowired
	public BussinessDelegate delegate;

	@GetMapping("/games")
	public String viewGames(Model model) {
		model.addAttribute("games",delegate.getAllGames() );
		return "juegos/ver";
	}

	@GetMapping("/games/add")
	public String addGame(Model model) {
		model.addAttribute("game", new TsscGame());
		model.addAttribute("topics", delegate.getAllTopics());
		return "juegos/add";
	}

	@GetMapping("/games/del/{id}")
	public String deleteGame(@PathVariable long id) {
		try {
			delegate.deleteGame(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/games";
	}
	
	

	@PostMapping("/games/edit/{id}")
	public String editGame(@PathVariable long id, @Validated @ModelAttribute("game") TsscGame game,
			BindingResult bindingResult, Model model) {

		try {
			if (bindingResult.hasErrors()) {		
				model.addAttribute("topics", delegate.getAllTopics());
				return "juegos/edit";
			}
			delegate.editGame(game);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/games";
	}

	@GetMapping("/games/edit/{id}")
	public String editGame(@PathVariable long id, Model model) {
		try {
			TsscGame game = delegate.getGame(id);
			model.addAttribute("game", game);			
			model.addAttribute("topics", delegate.getAllTopics());
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
			model.addAttribute("topics", delegate.getAllTopics());
			return "/juegos/add";
		} else {
			delegate.addGame(game);
		}

		return "redirect:/games";
	}

}
