package com.kbstar.msgproducer.controller;

import com.kbstar.msgproducer.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final KafkaProducerService kafkaProducerService;

    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String topic, @RequestBody String message) {
        kafkaProducerService.sendMessage(topic, message);
        return ResponseEntity.ok("Message sent to Kafka topic " + topic + ": " + message);
    }
}