package com.computacion.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscStoryException;
import com.computacion.model.exceptions.TsscStoryNotFound;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscStoryRepository;
import com.computacion.service.TsscGameService;
import com.computacion.service.TsscStoryService;

@RestController
public class TsscStoryRestController {
	
	@Autowired
	public TsscStoryRepository storyRepo;
	@Autowired
	public TsscStoryService storyService;
	
	@Autowired
	public TsscGameService gameService;
	
	@GetMapping("api/stories")
	public Iterable<TsscStory> getAllStories(){
		return storyRepo.findAll();
	}
	
	@GetMapping("api/stories/{id}")
	public TsscStory getStoryById(@PathVariable long id) {
		try {
			return this.storyService.getStory(id);
		} catch (TsscStoryNotFound e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	@PatchMapping("api/stories/edit")
	public TsscStory EditStory(@RequestBody TsscStory story) throws TsscStoryException, TsscStoryNotFound, TsscGameNotFoundException {
			return this.storyService.updateStory(story, story.getTsscGame().getId());
	}
	
	
	@PostMapping("api/stories/add")
	public TsscStory AddStory(@RequestBody TsscStory story) throws TsscTopicNotFoundException, TsscStoryException, TsscGameNotFoundException {
			return this.storyService.createStory(story, story.getTsscGame().getId());
			
	}
	
	@PostMapping("api/games/{id}/stories/add")
	public TsscStory AddStoryWithGame(@RequestBody TsscStory story) throws TsscTopicNotFoundException, TsscStoryException, TsscGameNotFoundException {
		
		System.out.println(story.getId()+" game:"+story.getTsscGame().getId());
		
			return this.storyService.createStory(story, story.getTsscGame().getId());
			
	}
	
	@DeleteMapping("api/stories/del")
	public void DeleteStory(@PathVariable long id) {
		this.storyRepo.deleteById(id);
	}
	
	@GetMapping("api/game/{id}/stories")
	public Iterable<TsscStory> getStoriesByGame(@PathVariable long id) throws TsscGameNotFoundException{
		TsscGame game=this.gameService.getGame(id);
		return this.storyRepo.findAllByTsscGame(game);
	}

}
