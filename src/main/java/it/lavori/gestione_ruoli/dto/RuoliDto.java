package it.lavori.gestione_ruoli.dto;

import java.util.ArrayList;
import java.util.List;

import it.lavori.gestione_ruoli.model.Ruoli;
import it.lavori.gestione_ruoli.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuoliDto {
	private Ruoli id;
	private String descrizione;
	private List<Utente> utenti=new ArrayList<>();
	

}
