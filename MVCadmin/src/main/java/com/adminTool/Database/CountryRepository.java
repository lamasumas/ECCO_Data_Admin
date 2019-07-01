package com.adminTool.Database;


import org.springframework.data.repository.CrudRepository;

public interface CountryRepository  extends  CrudRepository<Country, String>{

	public Country findByCountry(String country);
}
