package gr.stamateris.kafkawebsockets.controller;

import gr.stamateris.kafkawebsockets.service.KafkaPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class KafkaController {

    @Autowired
    KafkaPublisher kafkaPublisher;

    @MessageMapping("/send/message")
    public void writeMessageToKafka(String message) {
        kafkaPublisher.sendMessage(message);
    }
}
