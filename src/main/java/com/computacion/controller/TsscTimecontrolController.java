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
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.exceptions.TsscGameNotFoundException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscGameRepository;
import com.computacion.repository.TsscTimecontrolRepository;
import com.computacion.repository.TsscTopicRepository;
import com.computacion.service.TsscTimecontrolService;

@Controller
public class TsscTimecontrolController {
	
	@Autowired
	public BussinessDelegate delegate;
	

	@GetMapping("/timecontrols")
	public String viewTimecontrols(Model model) {
		model.addAttribute("timecontrols", delegate.getAllTimecontrols());
		return "cronogramas/ver";
	}

	@GetMapping("/timecontrols/add")
	public String addTimecontrol(Model model) {
		model.addAttribute("timecontrol", new TsscTimecontrol());
		model.addAttribute("games", delegate.getAllGames());
		model.addAttribute("topics", delegate.getAllTopics());
		return "cronogramas/add";
	}

	@GetMapping("/timecontrols/del/{id}")
	public String deleteTimecontrol(@PathVariable long id) {
		try {
			delegate.deleteTimecontrol(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/timecontrols";
	}
	
	

	@PostMapping("/timecontrols/edit/{id}")
	public String editTimecontrol(@PathVariable long id, @Validated @ModelAttribute("timecontrol") TsscTimecontrol timecontrol,
			BindingResult bindingResult, Model model) {

		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("games", delegate.getAllGames());
				model.addAttribute("topics", delegate.getAllTopics());
				return "cronogramas/edit";
			}else {
				delegate.editTimecontrol(timecontrol);
			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/timecontrols";
	}

	@GetMapping("/timecontrols/edit/{id}")
	public String editTimecontrol(@PathVariable long id, Model model) {
		try {
			TsscTimecontrol timecontrol = delegate.getTimecontrol(id);
			model.addAttribute("timecontrol", timecontrol);
			model.addAttribute("games", delegate.getAllGames());
			model.addAttribute("topics", delegate.getAllTopics());
			return "/cronogramas/edit";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/cronogramas";
		}
	}

	@PostMapping("/timecontrols/add")
	public String addTimecontrolPost(@Validated @ModelAttribute("timecontrol") TsscTimecontrol timecontrol, BindingResult bindingResult,
			Model model) throws TsscGameNotFoundException, TsscTopicNotFoundException{
		if (bindingResult.hasErrors()) {
			model.addAttribute("games", delegate.getAllGames());
			return "/cronogramas/add";
		} else {
			delegate.addTimecontrol(timecontrol);

		}

		return "redirect:/timecontrols";
	}
	

}
