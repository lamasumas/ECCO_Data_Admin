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
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;

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
	
	
	@RequestMapping("/")
	public String goHome() {
		return "index";
	}
	
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
	
	@RequestMapping("/deleteQuestionBeginnersIntro")
	public String deleteQuestionBeginnerIntro(Model model) {
		
		ArrayList<QuestionsBeginner> questionsFromMongo = new ArrayList<QuestionsBeginner>(StreamSupport.
				stream(beginnersRepository.findAll().spliterator(),false).collect(Collectors.toList()));
	
		
		model.addAttribute("questions",questionsFromMongo);
		model.addAttribute("selectedQuestion", new QuestionBeginner() );
		
		return "deleteQuestionBeginnersIntro";
	}
	
	@RequestMapping("/deleteQuestionBeginners")
	public String editQuestion(@ModelAttribute QuestionBeginner selectedQueston, Model model) 
	{
		QuestionsBeginner x = beginnersRepository.findFirstByQuestion(selectedQueston.getQuestionText());
		beginnersRepository.deleteById(x.getId());
		return "index";
	}
	
	@RequestMapping("/addQuestionBeginners")
	public String addQuestionBeginners( Model model, boolean questionError, boolean answerError) 
	{
		model.addAttribute("questionToSave", new SavedBeginnerQuestion());
		model.addAttribute("questionError", questionError);
		model.addAttribute("answerError", answerError);
		return "addQuestionBeginners";
	}
	@RequestMapping("/saveQuestionBeginner")
	public String saveQuestionBeginner(Model model, @ModelAttribute SavedBeginnerQuestion questionToSave)
	{
		ArrayList<String> possibleAnswers = new ArrayList<String>();
		if(questionToSave.getQuestion().isEmpty() || beginnersRepository.findFirstByQuestion(questionToSave.getQuestion())!=null )
		{
			System.out.println("Problem");
			return addQuestionBeginners(model, true, false);
		}
		for(int i= 1; i<=5; i++)
		{
			if(questionToSave.getAnswer(i) =="")
				return addQuestionBeginners(model, false, true);
			else if(questionToSave.getAnswer(i) == null)
				break;
			else
			{
			
				possibleAnswers.add(questionToSave.getAnswer(i)+"@"+questionToSave.getEnergyType(i)+"@"+questionToSave.getPoints(i));
			}
			
		}
		 beginnersRepository.save( new QuestionsBeginner(questionToSave.getQuestion(), possibleAnswers.toArray(new String[possibleAnswers.size()])));
		
		
		return "index";
	}
	
	@RequestMapping("/viewQuestionsAdvance")
	public String viewQuestionsAdvance(Model model)
	{

		ArrayList<QuestionsAdvance> questionsFromMongo = new ArrayList<QuestionsAdvance>(StreamSupport.
				stream(advanceRepository.findAll().spliterator(),false).collect(Collectors.toList()));
		ArrayList<QuestionAdvance> questionsToSend = new ArrayList<>();
		
		questionsFromMongo.forEach(questionMongo -> {
			
			ArrayList<Answer> answers = new ArrayList<Answer>(
					Arrays.asList(questionMongo.getAnswer()).stream()
					.map(theAnswerFormat -> theAnswerFormat.split("@") )
					.map(theSplittedAnswer -> (theSplittedAnswer.length >1)?  new Answer(theSplittedAnswer[0], theSplittedAnswer[1], 0): new Answer(theSplittedAnswer[0], "(Load next question)", 0))
					.collect(Collectors.toList()));
			questionsToSend.add(new QuestionAdvance(questionMongo.getQuestion(), answers, questionMongo.getPosition()));	
			
		});
		
		model.addAttribute("questions",questionsToSend);
		return "viewQuestionsAdvance";
	}
	
	@RequestMapping("/deleteQuestionAdvance")
	public String deleteQuestionAdvance(Model model)
	{

		ArrayList<QuestionsAdvance> questionsFromMongo = new ArrayList<QuestionsAdvance>(StreamSupport.
				stream(advanceRepository.findAll().spliterator(),false).collect(Collectors.toList()));
	
		
		model.addAttribute("questions",questionsFromMongo);
		model.addAttribute("selectedQuestion", new QuestionAdvance() );
		
		return "deleteQuestionAdvance";
	}
	
	@RequestMapping("/saveDeleteQuestionAdvance")
	public String saveDeltedQuestionAdvance(@ModelAttribute QuestionAdvance selectedQueston, Model model) 
	{
		QuestionsAdvance x = advanceRepository.findFirstByQuestion(selectedQueston.getQuestionText());
		advanceRepository.deleteById(x.getId());
		return "index";
	}
	
	@RequestMapping("/addQuestionAdvance")
	public String addQuestionAdvance( Model model, boolean questionError, boolean nextStepError, boolean positionError) 
	{
		SavedAdvanceQuestion temp = new SavedAdvanceQuestion();
		temp.setAnswer1("Yes");
		temp.setAnswer2("No");
		temp.setNextStep1("");
		model.addAttribute("questionToSave", temp);
		model.addAttribute("questionError", questionError);
		model.addAttribute("nextStepError", nextStepError);
		model.addAttribute("positionError", positionError);
		
		return "addQuestionAdvance";
	}
	
	@RequestMapping("/saveQuestionAdvance")
	public String saveQuestionAdvance(Model model, @ModelAttribute SavedAdvanceQuestion questionToSave)
	{
		ArrayList<String> possibleAnswers = new ArrayList<String>();
		if(questionToSave.getQuestion().isEmpty() || advanceRepository.findFirstByQuestion(questionToSave.getQuestion())!=null )
		{
			System.out.println("Problem");
			return addQuestionAdvance(model, true, false, false);
		}
		if(questionToSave.getPosition().isEmpty())
		{
			System.out.println("Problem");
			return addQuestionAdvance(model, false, false, true);
		} 
		if(questionToSave.getNextStep(2).isEmpty())
		{
			System.out.println("Problem");
			return addQuestionAdvance(model, false, true, false);
		}
		
		
		
		for(int i= 1; i<=2; i++)
		{	
				possibleAnswers.add(questionToSave.getAnswer(i)+"@"+questionToSave.getNextStep(i)+"@");			
		}
		 QuestionsAdvance preparedQuestion= new QuestionsAdvance(questionToSave.getQuestion(), possibleAnswers.toArray(new String[possibleAnswers.size()]), Integer.valueOf(questionToSave.getPosition()));
		 if(advanceRepository.findFirstByPosition(preparedQuestion.getPosition()) != null) 
		 {
			 for(int i = (int) advanceRepository.count(); i>=  preparedQuestion.getPosition(); i-- )
			 {
				 QuestionsAdvance documentToUpdate = advanceRepository.findFirstByPosition(i);
				 if(documentToUpdate != null)
				 {
					 documentToUpdate.setPosition(documentToUpdate.getPosition()+1);
					 advanceRepository.save(documentToUpdate);
				 }
			 }
			 
		 }
		 
		 advanceRepository.save(preparedQuestion );
		
		
		return "index";
	}
	
	
	
	
}

