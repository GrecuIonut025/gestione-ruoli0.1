package it.lavori.gestione_ruoli.kafka.producer;

import it.lavori.gestione_ruoli.dto.UtenteDto;


public interface ProducerService {
	
	/**
	 * send message in asynchronous way
	 * @param message
	 */
	void sendMessage( UtenteDto message);
	/**
	 * send message in synchronous way
	 * @param message
	 * @return ProducerRecord
	 * @throws BeOfflineSimulaterException
	 * if timeout with the connection with cluster kafka or catch InterruptedException or ExecutionException
	 */
	UtenteDto sendMessageSync(UtenteDto message) throws Exception;
}
