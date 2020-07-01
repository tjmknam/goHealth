package com.gohealth.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GoHealthService {
	
	public GoHealthService() {
	
	}
	
	public Map<String, Integer> getBigramParsed(String filePath) throws FileNotFoundException {
		Map<String, Integer> map = new HashMap<>();
		try {
			File file = new File(filePath);
			BufferedReader br;
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				// remove all punctuations
				String[] words = line.replaceAll("\\W+", " ").toLowerCase().split("\\s+");
				for (int i = 0; i < words.length - 1; i++) {
					StringBuilder sb = new StringBuilder();
					sb.append(words[i] + " " + words[i+1]);
					String combinedWord = sb.toString();
					map.put(combinedWord, map.getOrDefault(combinedWord, 0) + 1);
				}
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("File was not found");
			throw new FileNotFoundException();
		}
		catch(IOException e) {
			System.out.println("Unable to parse the file");
			return map;
		}
		
		return map;
		
		
		
	}

}
