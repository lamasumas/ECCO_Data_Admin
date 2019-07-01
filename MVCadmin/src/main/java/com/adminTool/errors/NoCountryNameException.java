package com.adminTool.errors;

public class NoCountryNameException extends Exception 
{
	
	boolean thereIsAnError;
	public NoCountryNameException(boolean isError) {
		thereIsAnError= isError;
	}
	
	
	public boolean isThereIsAnError() {
		return thereIsAnError;
	}
	public void setThereIsAnError(boolean thereIsAnError) {
		this.thereIsAnError = thereIsAnError;
	}
}
