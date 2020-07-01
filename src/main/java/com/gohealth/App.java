package com.gohealth;

import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gohealth.service.GoHealthService;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.print("Specifiy location of the text file : ");
		Scanner scan = new Scanner(System.in);
		String filePath = scan.nextLine();
		GoHealthService service = new GoHealthService();
		try {
			Map<String, Integer> result = service.getBigramParsed(filePath);
			for(Map.Entry entry : result.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
		catch(Exception e) {
			System.out.println("Error is thrown : " + e);
		}
		
		
	}
}
