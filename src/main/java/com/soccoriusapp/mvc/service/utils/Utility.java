package com.soccoriusapp.mvc.service.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class Utility {

	private final SecureRandom RANDOM =  new SecureRandom();
	private final String ALPHABET_CODE = "ABCDEFGHIJKLMNOPQRSTUVXWYZ";
	//private final String ALPHABET_GENERAL = ALPHABET_CODE+""+ ALPHABET_CODE.toLowerCase();
	
	public String getGeneratedPacienteCode(int lengthOfLatter, int lengthOfNumbers) {
		return generatePacienteCode(lengthOfLatter,lengthOfNumbers);
	}
	
	private String generatePacienteCode(int lengthOfLatter, int lengthOfNumbers) {
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < lengthOfLatter; i++)
			builder.append(ALPHABET_CODE.charAt(RANDOM.nextInt(ALPHABET_CODE.length())));
		
		for(int i = 0; i < lengthOfNumbers; i++) 
			builder.append(RANDOM.nextInt(9));
		
		return new String(builder);
	}
}
