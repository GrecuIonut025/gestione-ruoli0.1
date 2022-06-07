package it.lavori.gestione_ruoli.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.lavori.gestione_ruoli.dto.UtenteDto;

public class UtenteConverter {

	public static String convertToUtenteJson(UtenteDto message) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper(); 
		return objectMapper.writeValueAsString(message);
	}
	public static UtenteDto convertJsonToUtenteDto(String utenteJson) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper(); 
		return objectMapper.readValue(utenteJson, UtenteDto.class);
	}
}
