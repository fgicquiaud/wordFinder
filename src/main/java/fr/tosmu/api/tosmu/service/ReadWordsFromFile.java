package fr.tosmu.api.tosmu.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ReadWordsFromFile {
	
	List<String> readAll();
	
	List<String> containsLetters(String letters, int lengthWord, String lettersOut);
	

}
