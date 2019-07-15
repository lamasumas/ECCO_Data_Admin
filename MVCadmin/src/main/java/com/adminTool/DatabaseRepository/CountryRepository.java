package com.adminTool.DatabaseRepository;


import org.springframework.data.repository.CrudRepository;

import com.adminTool.DatabaseDocuments.Country;

public interface CountryRepository  extends  CrudRepository<Country, String>{

	public Country findByCountry(String country);
}
