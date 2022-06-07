package it.lavori.gestione_ruoli.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.lavori.gestione_ruoli.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{

	
	Page<Utente> getUtenteByNome(Pageable page, String nome);

	
	

}
