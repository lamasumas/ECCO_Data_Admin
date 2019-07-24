package com.adminTool.DatabaseRepository;


import org.springframework.data.repository.CrudRepository;

import com.adminTool.DatabaseDocuments.Country;

/**
 * 
 * @author Eduardo Lamas Suárez
 * This interface is for creating a connection with the country collection
 *
 */
public interface CountryRepository  extends  CrudRepository<Country, String>{

	public Country findByCountry(String country);
}
