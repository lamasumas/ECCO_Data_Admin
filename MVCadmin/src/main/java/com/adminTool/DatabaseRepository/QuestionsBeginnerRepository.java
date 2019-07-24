package com.adminTool.DatabaseRepository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminTool.DatabaseDocuments.QuestionsBeginner;




/**
 * 
 * @author Eduardo Lamas Suárez
 * This interface is for creating a connection with the beginner collection
 *
 */
public interface QuestionsBeginnerRepository extends CrudRepository<QuestionsBeginner, String>{ 
	
	public QuestionsBeginner findFirstByQuestion(String question);
}
