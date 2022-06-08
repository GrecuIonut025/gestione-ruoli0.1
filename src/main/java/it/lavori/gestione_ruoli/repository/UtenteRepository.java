package it.lavori.gestione_ruoli.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{

	
	Page<Utente> getUtenteByNome(Pageable page, String nome);

	String FILTER_UTENTE_NOME_AND_COGNOME_QUERY = "select b from Utente b where UPPER(b.nome) like CONCAT('%',UPPER(?1),'%') and UPPER(b.cognome) like CONCAT('%',UPPER(?2),'%')";
	  @Query(FILTER_UTENTE_NOME_AND_COGNOME_QUERY)
	    Page<Utente> findByFirstNameLikeAndLastNameLike(String nomeFilter, String cognomeFilter,Pageable pageable);

}
