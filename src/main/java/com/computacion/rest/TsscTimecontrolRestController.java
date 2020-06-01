package com.computacion.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.computacion.model.TsscTimecontrol;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTimecontrolNotFoundException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscTimecontrolRepository;
import com.computacion.service.TsscTimecontrolService;

@RestController
public class TsscTimecontrolRestController {
	
	@Autowired
	public TsscTimecontrolRepository timecontrolRepo;
	@Autowired
	public TsscTimecontrolService timecontrolService;
	
	@GetMapping("api/timecontrols")
	public Iterable<TsscTimecontrol> getAllTimecontrols(){
		return timecontrolRepo.findAll();
	}
	
	@GetMapping("api/timecontrols/{id}")
	public TsscTimecontrol getTimecontrolById(@PathVariable long id) {
		try {
			return this.timecontrolService.getTimecontrol(id);
		} catch (TsscTimecontrolNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	@PatchMapping("api/timecontrols/edit")
	public TsscTimecontrol EditTimecontrol(@RequestBody TsscTimecontrol timecontrol) throws TsscTimecontrolNotFoundException, TsscGameNotFoundException, TsscTopicNotFoundException {
			return this.timecontrolService.updateTimecontrol(timecontrol, timecontrol.getTsscGame().getId(),timecontrol.getTsscTopic().getId());
	}
	
	
	@PostMapping("api/timecontrols/add")
	public TsscTimecontrol AddTimecontrol(@RequestBody TsscTimecontrol timecontrol) throws TsscTopicNotFoundException, TsscGameNotFoundException {
			return this.timecontrolService.createTimecontrol(timecontrol, timecontrol.getTsscGame().getId(),timecontrol.getTsscTopic().getId());
			
	}
	
	@DeleteMapping("api/timecontrols/del")
	public void DeleteTimecontrol(@PathVariable long id) {
		this.timecontrolRepo.deleteById(id);
	}

}
