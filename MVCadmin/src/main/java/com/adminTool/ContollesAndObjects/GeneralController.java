package com.adminTool.ContollesAndObjects;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.EditorAwareTag;

import com.adminTool.Database.Country;
import com.adminTool.Database.CountryRepository;
import com.adminTool.Database.SimpleCountry;
import com.adminTool.errors.DuplicateCountryException;
import com.adminTool.errors.NoCountryNameException;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GeneralController {
	
	@Autowired
	private Sanitizer sanitizer;
	
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
		
		
		repository.save(sanitizer.isCorrectEdit(countryToEdit));
		return "index";
	}

	@RequestMapping("/add")
	public String addingNewCountry( Model model, boolean noCountryName, boolean duplicateCountry)
	{
		model.addAttribute("countryToEdit", new Country());
		model.addAttribute("duplicate", new DuplicateCountryException(duplicateCountry));
		model.addAttribute("noName", new NoCountryNameException(noCountryName)) ;
		
		return "add";
	}
	
	@RequestMapping("/savingAddedChanges")
	public String savingNewCountry(@ModelAttribute Country countryToEdit, Model model)
	{
		
		try {
			repository.save(sanitizer.isCorrect(countryToEdit));
			
		} catch (NoCountryNameException e) {
			return addingNewCountry(model, true, false);
		} catch (DuplicateCountryException e) {
			return addingNewCountry(model, false, true);
		}
		return "index";
	}
		
	@RequestMapping("/remove")
	public String remove(Model model)
	{
	
		ArrayList<Country> countries = new ArrayList<Country>();
		repository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("countries", countries);
		model.addAttribute("countryToRemove", new SimpleCountry());
		return "remove";
	}
	
	@RequestMapping("/removeCountry")
	public String removeCountry(@ModelAttribute SimpleCountry countryName, Model model)
	{
		repository.delete(repository.findByCountry(countryName.name));
		return "index";
	}
	
	
	
	
}

