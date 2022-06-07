package it.lavori.gestione_ruoli.kafka.producer;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.util.UtenteConverter;

@Service
public final class ProducerServiceImpl implements ProducerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

	@Value("${spring.kafka.topic.utente}")
	private String kafkaTopic;

	@Autowired
	KafkaTemplate<String, String> kafkaProducerTemplate;


	@Override
	public void sendMessage(UtenteDto message) {
		LOGGER.info("Producing message: {} ", message.toString());
		String messageString;
		try {
			messageString =  UtenteConverter.convertToUtenteJson(message);
		
		Message<String> kafkaMessage = MessageBuilder.withPayload(messageString).setHeader(KafkaHeaders.TOPIC, kafkaTopic)
				.build();
		ListenableFuture<SendResult<String, String>> future = kafkaProducerTemplate.send(kafkaMessage);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				LOGGER.info("Sent message=[ {} ] on topic=[ {} ] with offset=[ {} ]", message, kafkaTopic,
						result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOGGER.error("Unable to send message=[ {} ] on topic=[ {} ] due to : {} ", message, kafkaTopic,
						ex.getMessage());
			}
		});
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to convert to json {}", message.toString());
		}
	}

	@Override
	public UtenteDto sendMessageSync(UtenteDto message) throws Exception {
		LOGGER.info("sendMessageSync()");
		try {
			String messageString = UtenteConverter.convertToUtenteJson(message);
			
			Message<String> kafkaMessage = MessageBuilder.withPayload(messageString).setHeader(KafkaHeaders.TOPIC, kafkaTopic)
					.build();

			SendResult<String, String> response = kafkaProducerTemplate.send(kafkaMessage).get();
			ProducerRecord<String, String> record = response.getProducerRecord();
			
			LOGGER.info("Record return {}",record.toString());
			record.value();
			UtenteDto utente = UtenteConverter.convertJsonToUtenteDto(record.value());
			return utente;
		} catch (InterruptedException | ExecutionException | KafkaProducerException e) {
			LOGGER.error("Impossible to send message to kafka server with cause [ {} ], trace [ {} ]", e.getCause(),
					e.getStackTrace());
			throw new Exception("error send message to kafka");

		} 

	}
	

}