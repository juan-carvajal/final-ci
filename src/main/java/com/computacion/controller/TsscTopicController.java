package com.computacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.computacion.model.TsscStory;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscStoryException;
import com.computacion.model.exceptions.TsscStoryNotFound;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;
import com.computacion.repository.TsscStoryRepository;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscGameService;
import com.computacion.service.TsscStoryService;
import com.computacion.service.TsscTopicService;

@Controller
public class TsscTopicController {

	
	@Autowired
	public TsscTopicRepository topicRepo;

	@Autowired
	public TsscTopicService topicService;


	


	@GetMapping("/topics")
	public String viewTopics(Model model) {
		model.addAttribute("topics", topicRepo.findAll());
		return "temas/ver";
	}

	@GetMapping("/topics/add")
	public String addTopic(Model model) {
		model.addAttribute("topic", new TsscTopic());
		return "temas/add";
	}

	@GetMapping("/topics/del/{id}")
	public String deleteTopic(@PathVariable long id) {
		try {
			this.topicRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/topics";
	}
	
	

	@PostMapping("/topics/edit/{id}")
	public String editTopic(@PathVariable long id, @Validated @ModelAttribute("topic") TsscTopic topic,
			BindingResult bindingResult, Model model) {

		try {
			if (bindingResult.hasErrors()) {

				return "temas/edit";
			}else {
				this.topicService.updateTopic(topic);
			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/topics";
	}

	@GetMapping("/topics/edit/{id}")
	public String editTopic(@PathVariable long id, Model model) {
		try {
			TsscTopic topic = topicService.getTopic(id);
			model.addAttribute("topic", topic);
			return "/temas/edit";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/topics";
		}
	}

	@PostMapping("/topics/add")
	public String addTopicPost(@Validated @ModelAttribute("topic") TsscTopic topic, BindingResult bindingResult,
			Model model) throws TsscStoryException, TsscGameNotFoundException, TsscStoryNotFound, TsscTopicException, TsscTopicNotFoundException{
		if (bindingResult.hasErrors()) {
			for(ObjectError obj:bindingResult.getAllErrors()) {
				System.out.println(topic.getDefaultGroups());
				System.out.println(obj.getDefaultMessage());
			}
			return "/temas/add";
		} else {
			this.topicService.createTopic(topic);
			return "redirect:/topics";
		}

		
	}
}
