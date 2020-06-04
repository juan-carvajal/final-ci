package com.computacion.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.dao.TopicDao;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscTopicService;

@RestController
public class TsscTopicRestController {
	
	@Autowired
	public TsscTopicRepository topicRepo;
	@Autowired
	public TsscTopicService topicService;
	
	@Autowired
	public TopicDao topicDao;
	
	@GetMapping("api/topics")
	public Iterable<TsscTopic> getAllTopics(){
		return topicRepo.findAll();
	}
	
	@GetMapping("api/topics/{id}")
	public TsscTopic getTopicById(@PathVariable long id) {
		try {
			return this.topicService.getTopic(id);
		} catch (TsscTopicNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	@PatchMapping("api/topics/edit")
	public TsscTopic EditTopic(@RequestBody TsscTopic topic) throws TsscTopicNotFoundException, TsscTopicException {
			return this.topicService.updateTopic(topic);
	}
	
	
	@PostMapping("api/topics/add")
	public TsscTopic AddTopic(@RequestBody TsscTopic topic) throws TsscTopicNotFoundException, TsscTopicException {
			return this.topicService.createTopic(topic);
			
	}
	
	@DeleteMapping("api/topics/del")
	public void DeleteTopic(@PathVariable long id) {
		this.topicRepo.deleteById(id);
	}
	
	
	@GetMapping("api/topics/byDate/{date}")
	public Iterable<TsscTopic> getTopicByDate(@PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate date){
		System.out.println("Entro"+date.format(DateTimeFormatter.BASIC_ISO_DATE));
		var res= this.topicDao.listTopicWithGamesCountByDate(date);
		ArrayList<TsscTopic> topics=new ArrayList<>();
		for (int i = 0; i < res.size(); i++) {
			topics.add((TsscTopic)res.get(i)[0]);
		}
		return topics;
	}

}
