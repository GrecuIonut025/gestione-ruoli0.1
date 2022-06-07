package it.lavori.gestione_ruoli.dto;

import java.util.ArrayList;
import java.util.List;

import it.lavori.gestione_ruoli.model.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UtenteDto {

	private Long codice;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private List<Ruolo> ruoli = new ArrayList<>();

}
