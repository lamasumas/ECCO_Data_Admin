package com.adminTool.DatabaseDocuments;

/**
 * 
 * @author Eduardo Lamas Suárez
 * Document representation needed for accessing the country collection of the database
 */
public class SimpleCountry 
{
	public String name;
	
	
	public SimpleCountry() {
		name ="";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
