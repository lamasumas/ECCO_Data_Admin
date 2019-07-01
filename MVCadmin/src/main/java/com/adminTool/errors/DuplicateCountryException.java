package com.adminTool.errors;

public class DuplicateCountryException extends Exception  {
	
	boolean thereIsAnError;
	public DuplicateCountryException(boolean isError) {
		thereIsAnError= isError;
	}
	
	
	public boolean isThereIsAnError() {
		return thereIsAnError;
	}
	public void setThereIsAnError(boolean thereIsAnError) {
		this.thereIsAnError = thereIsAnError;
	}
}



