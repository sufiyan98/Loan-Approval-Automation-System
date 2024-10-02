package com.efrp.LoanApplicationSubmission.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.efrp.LoanApplicationSubmission.model.LoanApplicationCreatedEvent;

@Service
public class KafkaService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void publish(String topic,Object message) {
		kafkaTemplate.send(topic, message);
	}
}
