package com.kbstar.msgproducer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        String encodedMessage = encodeCustomEncoding(message);
        log.info("Sending message to topic {}: {}", topic, encodedMessage);
        kafkaTemplate.send(topic, encodedMessage);
    }

    /**
     * Encodes a string by converting Korean characters to 'C' followed by 4 hexadecimal digits.
     * This is the reverse of the decoding logic in msg-consumer.
     * Example: 그룹회사코드 -> Cadf8Cb8f9Cd68cCc0accCcf54Cb4dc
     *
     * @param originalString The string to encode.
     * @return The encoded string.
     */
    private String encodeCustomEncoding(String originalString) {
        if (originalString == null || originalString.isEmpty()) {
            return originalString;
        }

        StringBuilder encoded = new StringBuilder();
        for (char c : originalString.toCharArray()) {
            // Check if the character is a Korean character (Hangul Syllables, Hangul Jamo, Hangul Compatibility Jamo)
            if (c >= '\uAC00' && c <= '\uD7AF' || // Hangul Syllables
                c >= '\u1100' && c <= '\u11FF' || // Hangul Jamo
                c >= '\u3130' && c <= '\u318F') { // Hangul Compatibility Jamo
                encoded.append('C');
                encoded.append(String.format("%04x", (int) c));
            } else {
                encoded.append(c);
            }
        }
        return encoded.toString();
    }
}