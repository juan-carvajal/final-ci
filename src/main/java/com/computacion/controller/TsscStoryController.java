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

import com.computacion.delegate.BussinessDelegate;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscStoryException;
import com.computacion.model.exceptions.TsscStoryNotFound;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;
import com.computacion.repository.TsscStoryRepository;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscGameService;
import com.computacion.service.TsscStoryService;

@Controller
public class TsscStoryController {
	
	
	@Autowired
	public BussinessDelegate delegate;
	
	

	
	@GetMapping("/stories/byGame/{id}")
	public String getStoriesByGame(@PathVariable long id,Model model) throws TsscGameNotFoundException {
		try {
			model.addAttribute("stories", delegate.getStoriesByGame(id));
			model.addAttribute("gameID", id);
			return "historias/verPorGame";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/stories";
		}

	}

	@GetMapping("/stories")
	public String viewStories(Model model) {
		model.addAttribute("stories", delegate.getAllStories());
		return "historias/ver";
	}

	@GetMapping("/stories/add")
	public String addStory(Model model) {
		model.addAttribute("story", new TsscStory());
		model.addAttribute("games", delegate.getAllGames());
		return "historias/add";
	}

	@GetMapping("/stories/del/{id}")
	public String deleteStory(@PathVariable long id) {
		try {
			delegate.deleteStory(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/stories";
	}
	
	

	@PostMapping("/stories/edit/{id}")
	public String editStory(@PathVariable long id, @Validated @ModelAttribute("story") TsscStory story,
			BindingResult bindingResult, Model model) {

		try {
			if (bindingResult.hasErrors()) {
				//model.addAttribute("game", this.gameService.getGame(id));
				model.addAttribute("games", delegate.getAllGames());
				return "historias/edit";
			}else {
				delegate.editStory(story);
			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/stories";
	}

	@GetMapping("/stories/edit/{id}")
	public String editStory(@PathVariable long id, Model model) {
		try {
			TsscStory story = delegate.getStory(id);
			model.addAttribute("story", story);
			model.addAttribute("games", delegate.getAllGames());
			return "/historias/edit";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/historias";
		}
	}

	@PostMapping("/stories/add")
	public String addStoryPost(@Validated @ModelAttribute("story") TsscStory story, BindingResult bindingResult,
			Model model) throws TsscStoryException, TsscGameNotFoundException, TsscStoryNotFound{
		if (bindingResult.hasErrors()) {
			model.addAttribute("games", delegate.getAllGames());
			return "/historias/add";
		} else {
			delegate.addStory(story);

		}

		return "redirect:/stories";
	}

}
