package fr.tosmu.api.tosmu.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import fr.tosmu.api.tosmu.service.ReadWordsFromFile;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReadWordsFromFileService implements ReadWordsFromFile {
	
	private Map<String, Map<String, Integer>> mapWordsMapCount;
	
	@Override
	public List<String> readAll() {
		return readAllLinesFromFile().keySet().stream().toList();
	}
	
	@Override
	public List<String> containsLetters(String letters, int lengthWord, String lettersOut, String pattern) {
		Map<String, Integer> mapLettersIn = generateMapLettersCount(letters.toUpperCase());
		var retour = new ArrayList<String>();
		
		for(String word : this.getMapWordsMapCount().keySet().stream().filter(x -> (lengthWord != 0 && x.length() == lengthWord) || lengthWord == 0).collect(Collectors.toList())) {
			String patternWordOut = ".*[".concat(lettersOut.toUpperCase()).concat("].*");
			if(!"".equals(lettersOut) && word.matches(patternWordOut) )
				continue;
			if(!"".equals(pattern) && !word.matches(pattern.toUpperCase()))
				continue;
			
			boolean wordIsOk = true;
			for(String letterIn : mapLettersIn.keySet()) {
				if(this.getMapWordsMapCount().get(word) == null)
					this.getMapWordsMapCount().put(word, generateMapLettersCount(word));

				int countLettersIn = mapLettersIn.get(letterIn) != null ? mapLettersIn.get(letterIn) : 0;
				int countLettersWord = this.getMapWordsMapCount().get(word).get(letterIn) != null ?
						this.getMapWordsMapCount().get(word).get(letterIn) : 0;

				log.info("Lettre {} mot {} : cout mot {} count letters in {}",
						letterIn, word, countLettersWord, countLettersIn);
					
				if(countLettersWord == 0 || countLettersWord < countLettersIn) {
					wordIsOk = false;
					break;
				}

			}
			
			if(wordIsOk) {
				log.info("Word is ok : {}", wordIsOk);
				retour.add(word);
			}
		}
		
		return retour;
	}
	
	private Map<String, Map<String, Integer>> readAllLinesFromFile() {
		File resource;
		List<String> listReturned = null;
		try {
			resource = new ClassPathResource("words/french_words.txt").getFile();
			String text = new String(Files.readAllBytes(resource.toPath()), Charset.forName("UTF-8"));
			String[] arrayWords = text.split(";");
			
			listReturned  = Arrays.asList(arrayWords);
			
			if(this.mapWordsMapCount == null)
				this.mapWordsMapCount = new HashMap<>();
			
			listReturned.stream().forEach(word -> {
				this.mapWordsMapCount.put(word, null);
			});
			
		} catch (IOException e) {
			log.error("cant fking read the file");
		}
		
		return mapWordsMapCount;
	}
	
	private Map<String, Integer> generateMapLettersCount(String stringToCount) {
		Map<String, Integer> map = new HashMap<>();
		
		for(String letter : stringToCount.split("")) {
			if(map.containsKey(letter)) {
				Integer count = map.get(letter);
				map.put(letter, count + 1);
			} else {
				map.put(letter, 1);
			}
		}
		
		return map;
	}
	
	private Map<String, Map<String, Integer>> getMapWordsMapCount() {
		if(this.mapWordsMapCount == null)
			mapWordsMapCount = readAllLinesFromFile();
		
		return mapWordsMapCount;
		
	}

}
