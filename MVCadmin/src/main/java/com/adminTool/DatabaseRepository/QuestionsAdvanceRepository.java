package com.adminTool.DatabaseRepository;


import org.springframework.data.repository.CrudRepository;

import com.adminTool.DatabaseDocuments.QuestionsAdvance;
import com.adminTool.DatabaseDocuments.QuestionsBeginner;



public interface QuestionsAdvanceRepository extends CrudRepository<QuestionsAdvance, String>{ 
	public QuestionsAdvance findFirstByQuestion(String question);
	public QuestionsAdvance findFirstByPosition(int position);
}
