package com.adminTool.ContollesAndObjects;

import org.springframework.stereotype.Component;

import com.adminTool.Database.Country;
import com.adminTool.errors.NoCountryNameException;

@Component
public class Sanitizer 
{
	
	public Country isCorrect(Country theCountry) throws NoCountryNameException
	{
		if(theCountry.country.isEmpty())
			throw new NoCountryNameException(true);
		
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

	private String checkFloat(String attribute) {
		try {
			Float.parseFloat(attribute);
		}catch(Exception e) {
			return "";
		}
		return attribute;
	}

}
