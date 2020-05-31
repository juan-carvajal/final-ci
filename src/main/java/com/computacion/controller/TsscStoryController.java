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
	public TsscStoryRepository storyRepo;

	@Autowired
	public TsscStoryService storyService;

	@Autowired
	public TsscGameService gameService;
	
	@Autowired
	public TsscGameRepository gameRepo;
	

	
	@GetMapping("/stories/byGame/{id}")
	public String getStoriesByGame(@PathVariable long id,Model model) throws TsscGameNotFoundException {
		try {
			model.addAttribute("stories", storyRepo.findAllByTsscGame(gameService.getGame(id)));
			model.addAttribute("gameID", id);
			return "historias/verPorGame";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/stories";
		}

	}

	@GetMapping("/stories")
	public String viewStories(Model model) {
		model.addAttribute("stories", storyRepo.findAll());
		return "historias/ver";
	}

	@GetMapping("/stories/add")
	public String addStory(Model model) {
		model.addAttribute("story", new TsscStory());
		model.addAttribute("games", gameRepo.findAll());
		return "historias/add";
	}

	@GetMapping("/stories/del/{id}")
	public String deleteStory(@PathVariable long id) {
		try {
			this.storyRepo.deleteById(id);
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
				model.addAttribute("games", gameRepo.findAll());
				return "historias/edit";
			}else {
				this.storyService.updateStory(story, story.getTsscGame().getId());
			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/stories";
	}

	@GetMapping("/stories/edit/{id}")
	public String editStory(@PathVariable long id, Model model) {
		try {
			TsscStory story = storyService.getStory(id);
			model.addAttribute("story", story);
			model.addAttribute("games", gameRepo.findAll());
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
			model.addAttribute("games", gameRepo.findAll());
			return "/historias/add";
		} else {
			this.storyService.createStory(story, story.getTsscGame().getId());

		}

		return "redirect:/stories";
	}

}
