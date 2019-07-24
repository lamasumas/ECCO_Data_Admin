package com.adminTool.DatabaseDocuments;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Eduardo Lamas Suárez
 * Document class for accesing the Country collection of the database
 *
 */
@Document
public class Country {

	  @Id
	  public String id;
	  
	  public String  country;
	  public String  eelec;
	  public String  esaved_PV;
	  public String  esaved_wind;
	  public String  esaved_hydro;
	  public String  eheat;
	  public String  efuel;
	  public String  egas;
	  public String  fCH4_CO2;
	  public String  fN2O_CO2;
	  public String  nvehicle_Dry;
	  public String  etransport_exhaust_Dry;
	  public String  nvehicle_Liquid;
	  public String  etransport_exhaust_Liquid;
	  public String  LHV_Demolitoion_Wood;
	  public String  LHV_Sawdust;
	  public String  LHV_Methane;
	  public String  etree;
	  public String  ehouse;
	  
	  
	  public Country() { }
	  
	  public Country(
	   String  country,
	   String  eelec,
	   String  esaved_PV,
	   String  esaved_wind,
	   String  esaved_hydro,
	   String  eheat,
	   String  efuel,
	   String  egas,
	   String  fCH4_CO2,
	   String  fN2O_CO2,
	   String  nvehicle_Dry,
	   String  etransport_exhaust_Dry,
	   String  nvehicle_Liquid,
	   String  etransport_exhaust_Liquid,
	   String  LHV_Demolitoion_Wood,
	   String  LHV_Sawdust,
	   String  LHV_Methane,
	   String  etree,
	   String  ehouse) {
		  this.country = country;
		  this.eelec = eelec;
		  this.esaved_PV = esaved_PV;
		  this.esaved_wind = esaved_wind;
		  this.esaved_hydro = esaved_hydro;
		  this.eheat = eheat;
		  this.efuel = efuel;
		  this.egas = egas;
		  this.fCH4_CO2 = fCH4_CO2;
		  this.fN2O_CO2 = fN2O_CO2;
		  this.nvehicle_Dry = nvehicle_Dry;
		  this.etransport_exhaust_Dry  = etransport_exhaust_Dry;
		  this.nvehicle_Liquid = nvehicle_Liquid;
		  this.etransport_exhaust_Liquid = etransport_exhaust_Liquid ;
		  this.LHV_Demolitoion_Wood = LHV_Demolitoion_Wood ;
		  this.LHV_Sawdust = LHV_Sawdust;
		  this.LHV_Methane = LHV_Methane;
		  this.etree = etree;
		  this.ehouse = ehouse;
		  
}
	  
	  public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getEelec() {
			return eelec;
		}

		public void setEelec(String eelec) {
			this.eelec = eelec;
		}

		public String getEsaved_PV() {
			return esaved_PV;
		}

		public void setEsaved_PV(String esaved_PV) {
			this.esaved_PV = esaved_PV;
		}

		public String getEsaved_wind() {
			return esaved_wind;
		}

		public void setEsaved_wind(String esaved_wind) {
			this.esaved_wind = esaved_wind;
		}

		public String getEsaved_hydro() {
			return esaved_hydro;
		}

		public void setEsaved_hydro(String esaved_hydro) {
			this.esaved_hydro = esaved_hydro;
		}

		public String getEheat() {
			return eheat;
		}

		public void setEheat(String eheat) {
			this.eheat = eheat;
		}

		public String getEfuel() {
			return efuel;
		}

		public void setEfuel(String efuel) {
			this.efuel = efuel;
		}

		public String getEgas() {
			return egas;
		}

		public void setEgas(String egas) {
			this.egas = egas;
		}

		public String getfCH4_CO2() {
			return fCH4_CO2;
		}

		public void setfCH4_CO2(String fCH4_CO2) {
			this.fCH4_CO2 = fCH4_CO2;
		}

		public String getfN2O_CO2() {
			return fN2O_CO2;
		}

		public void setfN2O_CO2(String fN2O_CO2) {
			this.fN2O_CO2 = fN2O_CO2;
		}

		public String getNvehicle_Dry() {
			return nvehicle_Dry;
		}

		public void setNvehicle_Dry(String nvehicle_Dry) {
			this.nvehicle_Dry = nvehicle_Dry;
		}

		public String getEtransport_exhaust_Dry() {
			return etransport_exhaust_Dry;
		}

		public void setEtransport_exhaust_Dry(String etransport_exhaust_Dry) {
			this.etransport_exhaust_Dry = etransport_exhaust_Dry;
		}

		public String getNvehicle_Liquid() {
			return nvehicle_Liquid;
		}

		public void setNvehicle_Liquid(String nvehicle_Liquid) {
			this.nvehicle_Liquid = nvehicle_Liquid;
		}

		public String getEtransport_exhaust_Liquid() {
			return etransport_exhaust_Liquid;
		}

		public void setEtransport_exhaust_Liquid(String etransport_exhaust_Liquid) {
			this.etransport_exhaust_Liquid = etransport_exhaust_Liquid;
		}

		public String getLHV_Demolitoion_Wood() {
			return LHV_Demolitoion_Wood;
		}

		public void setLHV_Demolitoion_Wood(String lHV_Demolitoion_Wood) {
			LHV_Demolitoion_Wood = lHV_Demolitoion_Wood;
		}

		public String getLHV_Sawdust() {
			return LHV_Sawdust;
		}

		public void setLHV_Sawdust(String lHV_Sawdust) {
			LHV_Sawdust = lHV_Sawdust;
		}

		public String getLHV_Methane() {
			return LHV_Methane;
		}

		public void setLHV_Methane(String lHV_Methane) {
			LHV_Methane = lHV_Methane;
		}

		public String getEtree() {
			return etree;
		}

		public void setEtree(String etree) {
			this.etree = etree;
		}

		public String getEhouse() {
			return ehouse;
		}

		public void setEhouse(String ehouse) {
			this.ehouse = ehouse;
		}

		
	
}
