package it.lavori.gestione_ruoli.kafka.consumer;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.service.UtenteService;
import it.lavori.gestione_ruoli.util.UtenteConverter;

@Component
public class ConsumerServiceImpl implements ConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	

	@Value("${spring.kafka.topic.utente}")
	private String kafkaTopic;
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private DozerBeanMapper mapper;

	@KafkaListener(topics = "${spring.kafka.topic.utente}")
	public void listen(String utente) {

		try {
		UtenteDto utenteDto=UtenteConverter.convertJsonToUtenteDto(utente);
			LOGGER.info("Message received from topic '{}', message data: {}", kafkaTopic, utente);
			//convertire utenteDto in utente entity
			Utente u= mapper.map(utenteDto, Utente.class);
//			 List<Ruolo> ruoli=u.getRuoli();
//			u.setRuoli(null);
			
			UtenteDto utenteInserito=utenteService.insert(u);
//			for(Ruolo r:ruoli) {
//				r.setId(null);
//			}
			LOGGER.info("Successfully inserted user {}",utenteInserito);
			
		} catch (Exception e) {
			LOGGER.error("Something went wrong: {}", e.getMessage(), e);
		}
	}
}
