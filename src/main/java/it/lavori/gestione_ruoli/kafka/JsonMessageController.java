package it.lavori.gestione_ruoli.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.kafka.producer.ProducerService;

@RestController
@RequestMapping("api/v1/kafka")
public class JsonMessageController {
	
	@Autowired
	private ProducerService producerService;





	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody UtenteDto utente) {
		producerService.sendMessage(utente);
		return ResponseEntity.ok("Messaggio inviato al topic");
	}
}
