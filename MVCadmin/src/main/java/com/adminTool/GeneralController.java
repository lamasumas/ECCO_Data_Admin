package com.adminTool;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		model.addAttribute("selectedCountry", new SimpleCountry());
		model.addAttribute("countries", countries);
		return "editIntro";
	}

	@RequestMapping("/editCountry")
	public String setEditCountryForm(@ModelAttribute SimpleCountry simpleCountry, Model model)
	{

	
		
		Country choosenCountry = repository.findByCountry(simpleCountry.getName());
		
		model.addAttribute("countryToEdit", choosenCountry);
		return "editCountry";
	}
	
	
	@RequestMapping("/savingEditedChanges")
	public String saveEditedChanges(@ModelAttribute Country countryToEdit, Model model)
	{
		
		System.out.println("id" + countryToEdit.getId());
		System.out.println("coutnry" + countryToEdit.getCountry());
		System.out.println("eelec" + countryToEdit.getEelec());
		repository.save(countryToEdit);
		
		/*for(Country x: repository.findAll())
		{
			System.out.println("id" + x.getId());
			System.out.println("coutnry" + x.getCountry());
			System.out.println("eelec" + x.getEelec());
			
		}*/
		return "index";
	}
	
	
	
	
}

