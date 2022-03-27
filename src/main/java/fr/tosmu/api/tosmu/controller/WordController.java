package fr.tosmu.api.tosmu.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tosmu.api.tosmu.model.error.RestError;
import fr.tosmu.api.tosmu.model.response.search.SearchWordResponseModel;

@RestController
@RequestMapping("word")
public class WordController {
	
	@GetMapping("/test")
	public SearchWordResponseModel test() {
		
		SearchWordResponseModel test = new SearchWordResponseModel();
		
		test.setErrors(new ArrayList<RestError>());
		test.getErrors().add(new RestError("001", "message", "detail"));
		
		return test;
	}
	
	@GetMapping("/search")
	public SearchWordResponseModel search() {
		
		SearchWordResponseModel test = new SearchWordResponseModel();
		
		test.setErrors(new ArrayList<RestError>());
		test.getErrors().add(new RestError("001", "message", "detail"));
		
		return test;
	}
	
}
