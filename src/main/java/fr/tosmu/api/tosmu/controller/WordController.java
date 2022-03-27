package fr.tosmu.api.tosmu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		
		List<String> opt = readWordsFromFile.readAll().stream().filter(word -> word.contains(request.getLettres())).collect(Collectors.toList());
		
		response.setWordsFound(opt);
		
		return response;
	}
	
	@GetMapping("/all")
	public Integer coutAll() {
		var response = new SearchWordResponseModel();
		response.setWordsFound(readWordsFromFile.readAll());
		
		return response.getWordsFound().size();
	}
	
}
