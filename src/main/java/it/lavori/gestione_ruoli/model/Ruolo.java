package it.lavori.gestione_ruoli.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruolo implements Serializable {

    @Id
	@Enumerated(EnumType.STRING)
	@Column(name = "nome")
	private Ruoli id;

    private String descrizione;
    @JsonIgnore
    @ManyToMany(mappedBy = "ruoli", fetch = FetchType.EAGER)
    private List<Utente> utenti = new ArrayList<>();

	public void addUtente(Utente utente) {
		utenti.add(utente);
	}

	public void removeUtente(Utente utente) {
		utenti.remove(utente);
	}

}
