package fr.tosmu.api.tosmu.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import fr.tosmu.api.tosmu.service.ReadWordsFromFile;

@Service
public class ReadWordsFromFileService implements ReadWordsFromFile {
	
	@Override
	public List<String> readAll() {
		return readAllLinesFromFile();
	}
	
	@Override
	public List<String> containsLetters(String letters) {
		return readAllLinesFromFile().stream().filter(word -> contains(letters, word)).collect(Collectors.toList());
	}
	
	@Override
	public List<String> containsLetters(String letters, int lengthWord) {
		if(lengthWord != 0) {
			return readAllLinesFromFile().stream().filter(word -> contains(letters, word) && word.length() == lengthWord).collect(Collectors.toList());
		} else {
			return readAllLinesFromFile().stream().filter(word -> contains(letters, word)).collect(Collectors.toList());
		}
	}
	
	@Override
	public List<String> containsLetters(String letters, int lengthWord, String lettersOut) {
		if(lengthWord != 0) {
			return readAllLinesFromFile().stream().filter(word -> contains(letters, word) && word.length() == lengthWord && notContains(lettersOut, word)).collect(Collectors.toList());
		} else {
			return readAllLinesFromFile().stream().filter(word -> contains(letters, word) && notContains(lettersOut, word)).collect(Collectors.toList());
		}
	}
	
	private boolean contains(String letters, String word) {
		String[] arraysLetters = letters.split("");
		String[] arraysWord = word.split("");
		
		for(String letter : arraysLetters) {
			if(!word.contains(letter)) {
				return false;
			} else {
				if(countLetterInString(arraysWord, letter) < countLetterInString(arraysLetters, letter)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean notContains(String lettersOut, String word) {
		String[] arraysLetters = lettersOut.split("");
		
		for(String letter : arraysLetters) {
			if(word.contains(letter)) {
				return false;
			}
		}
		return true;
	}
	
	private List<String> readAllLinesFromFile() {
		File resource;
		List<String> listReturned = null;
		try {
			resource = new ClassPathResource("words/french_words.txt").getFile();
			String text = new String(Files.readAllBytes(resource.toPath()), Charset.forName("UTF-8"));
			
			String[] arrayWords = text.split("\n");
			
			listReturned  = Arrays.asList(arrayWords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listReturned;
	}
	
	private Long countLetterInString(String[] wordToCount, String letter) {
		return Arrays.stream(wordToCount).filter(arrayLetter -> arrayLetter.equals(letter)).count();
	}


}
