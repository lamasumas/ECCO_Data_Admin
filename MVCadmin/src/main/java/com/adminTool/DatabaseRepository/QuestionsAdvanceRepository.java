package com.adminTool.DatabaseRepository;


import org.springframework.data.repository.CrudRepository;

import com.adminTool.DatabaseDocuments.QuestionsAdvance;
import com.adminTool.DatabaseDocuments.QuestionsBeginner;



/**
 * 
 * @author Eduardo Lamas Suárez
 * This interface is for creating a connection with the advance question collection
 *
 */
public interface QuestionsAdvanceRepository extends CrudRepository<QuestionsAdvance, String>{ 
	public QuestionsAdvance findFirstByQuestion(String question);
	public QuestionsAdvance findFirstByPosition(int position);
}
