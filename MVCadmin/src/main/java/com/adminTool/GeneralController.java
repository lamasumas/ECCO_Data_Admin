package com.adminTool;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.EditorAwareTag;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GeneralController {
	@Autowired
	private CountryRepository repository;
	@RequestMapping("/view")
	public String setView(Model model)
	{

		ArrayList<Country> countries = new ArrayList<Country>();
		repository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("countries", countries);
		return "view";
	}
	
	@RequestMapping("/editIntro")
	public String setEditIntro(Model model)
	{

		ArrayList<Country> countries = new ArrayList<Country>();
		
		repository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("editedCountry",new Country());
		model.addAttribute("countries", countries);
		return "editIntro";
	}
}

