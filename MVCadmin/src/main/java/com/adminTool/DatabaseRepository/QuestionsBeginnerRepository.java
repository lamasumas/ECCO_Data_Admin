package com.adminTool.DatabaseRepository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminTool.DatabaseDocuments.QuestionsBeginner;




public interface QuestionsBeginnerRepository extends CrudRepository<QuestionsBeginner, String>{ 
	
	public QuestionsBeginner findByQuestion(String question);
}
