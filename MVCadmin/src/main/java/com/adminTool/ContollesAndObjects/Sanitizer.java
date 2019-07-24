package com.adminTool.ContollesAndObjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adminTool.DatabaseDocuments.Country;
import com.adminTool.DatabaseRepository.CountryRepository;
import com.adminTool.errors.DuplicateCountryException;
import com.adminTool.errors.NoCountryNameException;
/**
 * 
 * @author Eduardo Lamas Suárez	
 * Class made for sanitazing the data when adding or editing a country
 *
 */
@Component
public class Sanitizer 
{

	@Autowired
	private CountryRepository repository;
	
	
	/**
	 * Checks if a country has the correct data in it for add it to the database
	 * @param theCountry, Country to check
	 * @return Country checked
	 * @throws NoCountryNameException, Error if there is no name in the country
	 * @throws DuplicateCountryException, Error if there is already that country in the database
	 */
	public Country isCorrect(Country theCountry) throws NoCountryNameException, DuplicateCountryException
	{
		if(theCountry.country.isEmpty())
			throw new NoCountryNameException(true);
		
		if(repository.findByCountry(theCountry.country) != null)
			throw new DuplicateCountryException(true);
		
		theCountry.setEelec( checkFloat(theCountry.eelec));
		theCountry.setEfuel(checkFloat(theCountry.efuel));
		theCountry.setEgas(checkFloat(theCountry.egas));
		theCountry.setEheat(checkFloat(theCountry.eheat));
		theCountry.setEhouse(checkFloat(theCountry.ehouse));
		theCountry.setEsaved_hydro(checkFloat(theCountry.esaved_hydro));
		theCountry.setEsaved_PV(checkFloat(theCountry.esaved_PV));
		theCountry.setEsaved_wind(checkFloat(theCountry.esaved_wind));
		theCountry.setEtransport_exhaust_Dry(checkFloat(theCountry.etransport_exhaust_Dry));
		theCountry.setEtransport_exhaust_Liquid(checkFloat(theCountry.etransport_exhaust_Liquid));
		theCountry.setEtree(checkFloat(theCountry.etree));
		theCountry.setfCH4_CO2(checkFloat(theCountry.fCH4_CO2));
		theCountry.setfN2O_CO2(checkFloat(theCountry.fN2O_CO2));
		theCountry.setLHV_Demolitoion_Wood(checkFloat(theCountry.LHV_Demolitoion_Wood));
		theCountry.setLHV_Methane(checkFloat(theCountry.LHV_Methane));
		theCountry.setLHV_Sawdust(checkFloat(theCountry.LHV_Sawdust));
		theCountry.setNvehicle_Dry(checkFloat(theCountry.nvehicle_Dry));
		theCountry.setNvehicle_Liquid(checkFloat(theCountry.nvehicle_Liquid));
		
		return theCountry;
			
	}
	

	/**
	 * Checks if the data of a country that is being edited is correct
	 * @param theCountry, Country to check
	 * @return Country checked
	 */
	public Country isCorrectEdit(Country theCountry)
	{		
		theCountry.setEelec( checkFloat(theCountry.eelec));
		theCountry.setEfuel(checkFloat(theCountry.efuel));
		theCountry.setEgas(checkFloat(theCountry.egas));
		theCountry.setEheat(checkFloat(theCountry.eheat));
		theCountry.setEhouse(checkFloat(theCountry.ehouse));
		theCountry.setEsaved_hydro(checkFloat(theCountry.esaved_hydro));
		theCountry.setEsaved_PV(checkFloat(theCountry.esaved_PV));
		theCountry.setEsaved_wind(checkFloat(theCountry.esaved_wind));
		theCountry.setEtransport_exhaust_Dry(checkFloat(theCountry.etransport_exhaust_Dry));
		theCountry.setEtransport_exhaust_Liquid(checkFloat(theCountry.etransport_exhaust_Liquid));
		theCountry.setEtree(checkFloat(theCountry.etree));
		theCountry.setfCH4_CO2(checkFloat(theCountry.fCH4_CO2));
		theCountry.setfN2O_CO2(checkFloat(theCountry.fN2O_CO2));
		theCountry.setLHV_Demolitoion_Wood(checkFloat(theCountry.LHV_Demolitoion_Wood));
		theCountry.setLHV_Methane(checkFloat(theCountry.LHV_Methane));
		theCountry.setLHV_Sawdust(checkFloat(theCountry.LHV_Sawdust));
		theCountry.setNvehicle_Dry(checkFloat(theCountry.nvehicle_Dry));
		theCountry.setNvehicle_Liquid(checkFloat(theCountry.nvehicle_Liquid));
		
		return theCountry;
			
	}

	/**
	 * Checks if a string is a float
	 * @param attribute, String containing the float number
	 * @return String with the number
	 */
	private String checkFloat(String attribute) {
		try {
			Float.parseFloat(attribute);
		}catch(Exception e) {
			return "";
		}
		return attribute;
	}

}
