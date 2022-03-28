package fr.tosmu.api.tosmu.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tosmu.api.tosmu.model.error.RestError;
import fr.tosmu.api.tosmu.model.request.search.SearchWordRequestModel;
import fr.tosmu.api.tosmu.model.response.search.SearchWordResponseModel;
import fr.tosmu.api.tosmu.service.ReadWordsFromFile;

@RestController
@RequestMapping("word")
public class WordController {
	
	@Autowired
	private ReadWordsFromFile readWordsFromFile;
	
	@GetMapping("/test")
	public SearchWordResponseModel test() {
		var test = new SearchWordResponseModel();
		
		test.setErrors(new ArrayList<RestError>());
		test.getErrors().add(new RestError("001", "message", "detail"));
		
		return test;
	}
	
	@GetMapping("/search")
	public SearchWordResponseModel search(@RequestBody SearchWordRequestModel request) {
		var response = new SearchWordResponseModel();
		
		response.setWordsFound(readWordsFromFile.containsLetters(request.getLettres(), request.getLengthWord(), request.getLettersOut()));
		
		return response;
	}
	
	@GetMapping("/count")
	public Integer cout() {
		var response = new SearchWordResponseModel();
		response.setWordsFound(readWordsFromFile.readAll());
		
		return response.getWordsFound().size();
	}
	
	@GetMapping("/all")
	public SearchWordResponseModel all() {
		var response = new SearchWordResponseModel();
		response.setWordsFound(readWordsFromFile.readAll());
		
		return response;
	}
	
}
