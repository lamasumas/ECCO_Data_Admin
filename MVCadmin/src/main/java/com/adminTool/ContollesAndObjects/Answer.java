package com.adminTool.ContollesAndObjects;

/**
 * 
 * @author Eduardo Lamas Suarez
 * A class made in order to be able to manage better the answer
 * of the diferent question types
 *
 */
public class Answer 
{
	String answerText;
	String extraInfo;
	int points;
	
	public Answer(String answerText, String extraInfo, int points) {
		this.answerText = answerText;
		this.extraInfo = extraInfo;
		this.points = points;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * A method that creates the code for the beginners answer for the mongodb
	 * @return String with the code
	 */
	public String getRadioButtonBeginnerCode() {
		return extraInfo + "@" + points;
	}
	
	/**
	 * A method that creates the code for the advance answers
	 * @return String the code
	 */
	public String getRadioButtonAdvanceCode() {
		return extraInfo ;
	}
	

}
