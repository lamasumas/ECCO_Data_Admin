package mongoManager;
import org.springframework.data.annotation.Id; 

public class Country {

	
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
	  
	  public Country( String  country,
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
	
}
