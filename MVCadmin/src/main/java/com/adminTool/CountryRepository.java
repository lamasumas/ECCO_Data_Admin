package com.adminTool;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRepository  extends  MongoRepository<Country, String>{

	public Country findByCountry(String country);
}
