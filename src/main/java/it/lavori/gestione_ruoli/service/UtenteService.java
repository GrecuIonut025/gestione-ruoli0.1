package it.lavori.gestione_ruoli.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.repository.UtenteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {

	private final UtenteRepository utenteRepository;

	@Autowired
	private DozerBeanMapper mapper;

	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	public List<UtenteDto> findAll(){
		return utenteRepository.findAll().stream()
				.map(e->mapper.map(e,UtenteDto.class))
				.collect(Collectors.toList());
	}

	public void deleteAllUtenti(List<UtenteDto> utenti) {
		utenteRepository.deleteAll();
	}

	public UtenteDto insert(Utente utente) {
		
			return mapper.map(utenteRepository.save(utente), UtenteDto.class);
		
	 
		}

	public void delete(Long codice) {
		
			utenteRepository.deleteById(codice);
		
	}

	public UtenteDto update(Utente utente) {
		
			return mapper.map(utenteRepository.save(utente), UtenteDto.class);
	
	}

////	
	public UtenteDto getById(Long codice) throws NotFoundException {
		return mapper.map(utenteRepository.getById(codice), UtenteDto.class);

	}
//
//	public List<Ruolo> getRuoliByCodice(Long codice) {
//		Optional utenteOpt = utenteRepository.findById(codice);
//		List<Ruolo> lista = utenteOpt.isPresent() ? ((UtenteDto) utenteOpt.get()).getRuoli() : new ArrayList<Ruolo>();
//		return lista;
//	}	

	public UtenteDto getUtenteByNome(Pageable page, String nome) {
		return mapper.map(utenteRepository.getUtenteByNome(page, nome), UtenteDto.class);
	}
}
