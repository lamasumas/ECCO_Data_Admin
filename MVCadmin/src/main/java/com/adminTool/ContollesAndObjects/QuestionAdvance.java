package com.adminTool.ContollesAndObjects;

import java.util.ArrayList;

/**
 * 
 * @author Eduardo Lamas Suárez
 * Class made for holding and managing the advance questions
 *
 */
public class QuestionAdvance extends QuestionBeginner
{
	int position;
	


public QuestionAdvance(String questionText, ArrayList<Answer> answerList, int position) {
	super(questionText, answerList);
	this.position = position;
}


public QuestionAdvance() {
	// TODO Auto-generated constructor stub
}


public int getPosition() {
		return position;
	}


public void setPosition(int position) {
	this.position = position;
}


}
