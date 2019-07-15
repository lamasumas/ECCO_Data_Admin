package com.adminTool.ContollesAndObjects;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.EditorAwareTag;

import com.adminTool.DatabaseDocuments.Country;
import com.adminTool.DatabaseDocuments.QuestionsAdvance;
import com.adminTool.DatabaseDocuments.QuestionsBeginner;
import com.adminTool.DatabaseDocuments.SimpleCountry;
import com.adminTool.DatabaseRepository.CountryRepository;
import com.adminTool.DatabaseRepository.QuestionsAdvanceRepository;
import com.adminTool.DatabaseRepository.QuestionsBeginnerRepository;
import com.adminTool.errors.DuplicateCountryException;
import com.adminTool.errors.NoCountryNameException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GeneralController {
	
	@Autowired
	private Sanitizer sanitizer;
	
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private QuestionsBeginnerRepository beginnersRepository;
	
	@Autowired
	private QuestionsAdvanceRepository advanceRepository;
	
	@RequestMapping("/view")
	public String setView(Model model)
	{

		ArrayList<Country> countries = new ArrayList<Country>();
		countryRepository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("countries", countries);
		return "view";
	}
	
	@RequestMapping("/editIntro")
	public String setEditIntro(Model model)
	{

		ArrayList<Country> countries = new ArrayList<Country>();
		
		countryRepository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("selectedCountry", new SimpleCountry());
		model.addAttribute("countries", countries);
		return "editIntro";
	}

	@RequestMapping("/editCountry")
	public String setEditCountryForm(@ModelAttribute SimpleCountry simpleCountry, Model model)
	{

	
		
		Country choosenCountry = countryRepository.findByCountry(simpleCountry.getName());
		
		model.addAttribute("countryToEdit", choosenCountry);
		return "editCountry";
	}
	
	
	@RequestMapping("/savingEditedChanges")
	public String saveEditedChanges(@ModelAttribute Country countryToEdit, Model model)
	{
		
		
		countryRepository.save(sanitizer.isCorrectEdit(countryToEdit));
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
			countryRepository.save(sanitizer.isCorrect(countryToEdit));
			
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
		countryRepository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("countries", countries);
		model.addAttribute("countryToRemove", new SimpleCountry());
		return "remove";
	}
	
	@RequestMapping("/removeCountry")
	public String removeCountry(@ModelAttribute SimpleCountry countryName, Model model)
	{
		countryRepository.delete(countryRepository.findByCountry(countryName.name));
		return "index";
	}
	
	
	@RequestMapping("/viewQuestionsBeginners")
	public String viewQuestionsBeginners(Model model) {
		
		ArrayList<QuestionsBeginner> questionsFromMongo = new ArrayList<QuestionsBeginner>(StreamSupport.
				stream(beginnersRepository.findAll().spliterator(),false).collect(Collectors.toList()));
		ArrayList<QuestionBeginner> questionsToSend = new ArrayList<>();
		
		questionsFromMongo.forEach(questionMongo -> {
			
			ArrayList<Answer> answers = new ArrayList<Answer>(
					Arrays.asList(questionMongo.getAnswers()).stream()
					.map(theAnswerFormat -> theAnswerFormat.split("@") )
					.map(theSplittedAnswer ->  new Answer(theSplittedAnswer[0], theSplittedAnswer[1], Integer.valueOf(theSplittedAnswer[2])))
					.collect(Collectors.toList()));
			questionsToSend.add(new QuestionBeginner(questionMongo.getQuestion(), answers));	
			
		});
		
		model.addAttribute("questions",questionsToSend);
		
		return "viewQuestionsBeginners";
	}
	
	
	
	
}

