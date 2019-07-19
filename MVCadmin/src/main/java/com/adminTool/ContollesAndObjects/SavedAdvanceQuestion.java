package com.adminTool.ContollesAndObjects;

public class SavedAdvanceQuestion {
	private String question;
	private String answer1;
	private String answer2;
	private String nextStep1;
	private String nextStep2;
	private String position;
	

	public SavedAdvanceQuestion() {}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getNextStep1() {
		return nextStep1;
	}
	public void setNextStep1(String nextStep1) {
		this.nextStep1 = nextStep1;
	}
	public String getNextStep2() {
		return nextStep2;
	}
	public void setNextStep2(String nextStep2) {
		this.nextStep2 = nextStep2;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAnswer(int number) {
		switch(number)
		{
			case 1: return getAnswer1();
			case 2: return getAnswer2();
		}
		return "error";
	}
	
	public String getNextStep(int number) {
		switch(number)
		{
			case 1: return getNextStep1();
			case 2: return getNextStep2();
		}
		return "error";
	}
}
