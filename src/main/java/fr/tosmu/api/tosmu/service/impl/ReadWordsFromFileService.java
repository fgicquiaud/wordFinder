package fr.tosmu.api.tosmu.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import fr.tosmu.api.tosmu.service.ReadWordsFromFile;

@Service
public class ReadWordsFromFileService implements ReadWordsFromFile {
	
	public List<String> readAll() {
		return testResourceFile();
	}
	
	private List<String> testResourceFile() {
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
}
