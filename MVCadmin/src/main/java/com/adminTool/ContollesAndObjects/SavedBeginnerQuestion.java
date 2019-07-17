package com.adminTool.ContollesAndObjects;

public class SavedBeginnerQuestion
{
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private int points1;
	private int points2;
	private int points3;
	private int points4;
	private int points5;
	private String energyType1;
	private String energyType2;
	private String energyType3;
	private String energyType4;
	private String energyType5;
	
	
	
	public SavedBeginnerQuestion() {}
	
	
	
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
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	public int getPoints1() {
		return points1;
	}
	public void setPoints1(int points1) {
		this.points1 = points1;
	}
	public int getPoints2() {
		return points2;
	}
	public void setPoints2(int points2) {
		this.points2 = points2;
	}
	public int getPoints3() {
		return points3;
	}
	public void setPoints3(int points3) {
		this.points3 = points3;
	}
	public int getPoints4() {
		return points4;
	}
	public void setPoints4(int points4) {
		this.points4 = points4;
	}
	public int getPoints5() {
		return points5;
	}
	public void setPoints5(int points5) {
		this.points5 = points5;
	}
	public String getEnergyType1() {
		return energyType1;
	}
	public void setEnergyType1(String energyType1) {
		this.energyType1 = energyType1;
	}
	public String getEnergyType2() {
		return energyType2;
	}
	public void setEnergyType2(String energyType2) {
		this.energyType2 = energyType2;
	}
	public String getEnergyType3() {
		return energyType3;
	}
	public void setEnergyType3(String energyType3) {
		this.energyType3 = energyType3;
	}
	public String getEnergyType4() {
		return energyType4;
	}
	public void setEnergyType4(String energyType4) {
		this.energyType4 = energyType4;
	}
	public String getEnergyType5() {
		return energyType5;
	}
	public void setEnergyType5(String energyType5) {
		this.energyType5 = energyType5;
	}
	
	public String getAnswer(int number) {
		switch(number)
		{
			case 1: return getAnswer1();
			case 2: return getAnswer2();
			case 3: return getAnswer3();
			case 4: return getAnswer4();
			case 5: return getAnswer5();
		}
		return "error";
	}
	
	public int getPoints(int number)
	{
		switch(number) 
		{
			case 1: return getPoints1();
			case 2: return getPoints2();
			case 3: return getPoints3();
			case 4: return getPoints4();
			case 5: return getPoints5();
		}
		return -1;
		
	}
	
	public String getEnergyType(int number) {
		switch(number)
		{
			case 1: return getEnergyType1();
			case 2: return getEnergyType2();
			case 3: return getEnergyType3();
			case 4: return getEnergyType4();
			case 5: return getEnergyType5();
		}
		return "error";
	}
		

}
