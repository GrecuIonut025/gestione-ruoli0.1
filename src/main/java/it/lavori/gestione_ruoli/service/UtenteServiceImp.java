//package it.lavori.gestione_ruoli.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.dozer.DozerBeanMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import it.lavori.gestione_ruoli.dto.UtenteDto;
//import it.lavori.gestione_ruoli.repository.UtenteRepository;
//
//@Service
//public class UtenteServiceImp {
//	@Autowired
//	private UtenteRepository repository;
//	@Autowired
//	private DozerBeanMapper mapper;
//
//	public  List<UtenteDto> getAll() {
//		return repository.findAll().stream().map(entity -> mapper.map(entity, UtenteDto.class))
//				.collect(Collectors.toList());
//	}
//}
