package it.lavori.gestione_ruoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lavori.gestione_ruoli.model.Ruoli;
import it.lavori.gestione_ruoli.model.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, Ruoli> {

}
