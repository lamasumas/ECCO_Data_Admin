package com.adminTool.ContollesAndObjects;

import java.util.ArrayList;

public class QuestionAdvance extends QuestionBeginner
{
	int position;
	


public QuestionAdvance(String questionText, ArrayList<Answer> answerList, int position) {
	super(questionText, answerList);
	this.position = position;
}


public int getPosition() {
		return position;
	}


public void setPosition(int position) {
	this.position = position;
}


}
