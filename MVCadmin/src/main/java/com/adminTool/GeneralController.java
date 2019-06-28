package com.adminTool;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GeneralController {
	@Autowired
	private CountryRepository repository;
	@RequestMapping("/view")
	public String setView()
	{

		System.out.println("Searching mongodb");
		repository.findAll().forEach((x) ->{
			
		System.out.println(x.country);
		});
		
		return "view";
	}
}

