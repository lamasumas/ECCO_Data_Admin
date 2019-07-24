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
/**
 * 
 * @author Eduardo Lamas Suárez
 * Class that acts as the controller of the mvc project
 *
 */
@Controller
public class GeneralController {
	
	@Autowired
	private Sanitizer sanitizer;
	
	/**
	 * Mongodb manager for the country collection
	 */
	@Autowired
	private CountryRepository countryRepository;
	
	/**
	 * Mongodb manager for the beginner question collection
	 */
	@Autowired
	private QuestionsBeginnerRepository beginnersRepository;
	
	/**
	 * Mongodb manager for the advance question collection
	 */
	@Autowired
	private QuestionsAdvanceRepository advanceRepository;
	
	/**
	 * Mapping for going to the index page
	 * @return the page to go
	 */
	@RequestMapping("/")
	public String goHome() {
		return "index";
	}
	
	/**
	 * Mapping for going to the view countries page.
	 * Internally, the method download all the countries from the database
	 * an it stores it in an arraylist that is pass to the html
	 * @param model
	 * @return the page to go
	 */
	@RequestMapping("/view")
	public String setView(Model model)
	{

		ArrayList<Country> countries = new ArrayList<Country>();
		countryRepository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("countries", countries);
		return "view";
	}
	
	/**
	 * Mapping for the edit country intro page
	 * Internally it does more or less the same that the setView method
	 * @param model
	 * @return
	 */
	@RequestMapping("/editIntro")
	public String setEditIntro(Model model)
	{

		ArrayList<Country> countries = new ArrayList<Country>();
		
		countryRepository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("selectedCountry", new SimpleCountry());
		model.addAttribute("countries", countries);
		return "editIntro";
	}

	/**
	 * This is the mapping fot the actual editing page of the tool
	 * it simply pass the country to edit to the next page
	 * @param simpleCountry, selected country of the previous page
	 * @param model
	 * @return the next page to load
	 */
	@RequestMapping("/editCountry")
	public String setEditCountryForm(@ModelAttribute SimpleCountry simpleCountry, Model model)
	{

	
		
		Country choosenCountry = countryRepository.findByCountry(simpleCountry.getName());
		
		model.addAttribute("countryToEdit", choosenCountry);
		return "editCountry";
	}
	
	/**
	 * This mapping is called after the edition of the country data.
	 * It is used to store the country changes, after checking that all
	 * the new data is correct
	 * @param countryToEdit, the country to save
	 * @param model
	 * @return the next page to load
	 */
	@RequestMapping("/savingEditedChanges")
	public String saveEditedChanges(@ModelAttribute Country countryToEdit, Model model)
	{
		
		
		countryRepository.save(sanitizer.isCorrectEdit(countryToEdit));
		return "index";
	}

	/**
	 * This is the mapping used to load the adding country page
	 * @param model
	 * @param noCountryName, boolean used to show the error of a country withou a name
	 * @param duplicateCountry, boolean used to show the error of a duplicate country
	 * @return the next page to load
	 */
	@RequestMapping("/add")
	public String addingNewCountry( Model model, boolean noCountryName, boolean duplicateCountry)
	{
		model.addAttribute("countryToEdit", new Country());
		model.addAttribute("duplicate", new DuplicateCountryException(duplicateCountry));
		model.addAttribute("noName", new NoCountryNameException(noCountryName)) ;
		
		return "add";
	}
	
	/**
	 * This is the mapping used to save the new country after is checked
	 * that is correctly added the info
	 * @param countryToEdit, the new country
	 * @param model
	 * @return the next page to load
	 */
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
	
	/**
	 * Mapping for removing a country from the database
	 * @param model
	 * @return the next page to load
	 */
	@RequestMapping("/remove")
	public String remove(Model model)
	{
	
		ArrayList<Country> countries = new ArrayList<Country>();
		countryRepository.findAll().forEach((x) -> countries.add(x));
		
		model.addAttribute("countries", countries);
		model.addAttribute("countryToRemove", new SimpleCountry());
		return "remove";
	}
	
	/**
	 * Saving the action of the remove
	 * @param countryName, the country name of the country to remove
	 * @param model
	 * @return the next page to load
	 */
	@RequestMapping("/removeCountry")
	public String removeCountry(@ModelAttribute SimpleCountry countryName, Model model)
	{
		countryRepository.delete(countryRepository.findByCountry(countryName.name));
		return "index";
	}
	
	/**
	 * Mapping for view the beginners questions.
	 * It search it the database in a functional way
	 * @param model
	 * @return the next page to load
	 */
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
	
	/**
	 * Mapping for the removing a question 
	 * @param model
	 * @return the next page to load
	 */
	@RequestMapping("/deleteQuestionBeginnersIntro")
	public String deleteQuestionBeginnerIntro(Model model) {
		
		ArrayList<QuestionsBeginner> questionsFromMongo = new ArrayList<QuestionsBeginner>(StreamSupport.
				stream(beginnersRepository.findAll().spliterator(),false).collect(Collectors.toList()));
	
		
		model.addAttribute("questions",questionsFromMongo);
		model.addAttribute("selectedQuestion", new QuestionBeginner() );
		
		return "deleteQuestionBeginnersIntro";
	}
	/**
	 * Mapping used to actually delete the selected beginner question 
	 * @param selectedQueston, the question to delete
	 * @param model
	 * @return the next page to load 
	 */
	@RequestMapping("/deleteQuestionBeginners")
	public String editQuestion(@ModelAttribute QuestionBeginner selectedQueston, Model model) 
	{
		QuestionsBeginner x = beginnersRepository.findFirstByQuestion(selectedQueston.getQuestionText());
		beginnersRepository.deleteById(x.getId());
		return "index";
	}
	/**
	 * Mapping for adding a new question
	 * @param model
	 * @param questionError, boolean that appear when there is a problem in the actual question
	 * @param answerError, boolean that appear when there is a problem with the answer
	 * @return the next page to load
	 */
	@RequestMapping("/addQuestionBeginners")
	public String addQuestionBeginners( Model model, boolean questionError, boolean answerError) 
	{
		model.addAttribute("questionToSave", new SavedBeginnerQuestion());
		model.addAttribute("questionError", questionError);
		model.addAttribute("answerError", answerError);
		return "addQuestionBeginners";
	}
	
	
	/**
	 * This the mapping of the actual adding of the beginner question  
	 * @param model
	 * @param questionToSave, question that is going to be added to the database
	 * @return the next page to load
	 */
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
	
	/**
	 * Mapping for the page of showing the advance question to the admin
	 * @param model
	 * @return the page to load
	 */
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
	
	/**
	 * Mapping for deleting an advance question 
	 * @param model
	 * @return the next page to load
	 */
	@RequestMapping("/deleteQuestionAdvance")
	public String deleteQuestionAdvance(Model model)
	{

		ArrayList<QuestionsAdvance> questionsFromMongo = new ArrayList<QuestionsAdvance>(StreamSupport.
				stream(advanceRepository.findAll().spliterator(),false).collect(Collectors.toList()));
	
		
		model.addAttribute("questions",questionsFromMongo);
		model.addAttribute("selectedQuestion", new QuestionAdvance() );
		
		return "deleteQuestionAdvance";
	}
	
	/**
	 * The actual action of saving the deleted quesiton in the database
	 * @param selectedQueston, question to delted
	 * @param model
	 * @return the next page to load
	 */
	@RequestMapping("/saveDeleteQuestionAdvance")
	public String saveDeltedQuestionAdvance(@ModelAttribute QuestionAdvance selectedQueston, Model model) 
	{
		QuestionsAdvance x = advanceRepository.findFirstByQuestion(selectedQueston.getQuestionText());
		advanceRepository.deleteById(x.getId());
		return "index";
	}
	
	/**
	 * Mapping for adding the an advance quesiton
	 * @param model
	 * @param questionError, boolean for showing an actual quesiton error
	 * @param nextStepError, boolean for showing that there is no next step added
	 * @param positionError, boolean for showing that there is no position added
	 * @return the next page to load
	 */
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
	/**
	 * Mapping for the actual action of saving the advance quesiton
	 * @param model
	 * @param questionToSave, the question to save
	 * @return the next page to load
	 */
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

