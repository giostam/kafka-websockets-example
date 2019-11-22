package gr.stamateris.kafkawebsockets.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "Topic1")
    public void listen(String message) {
        log.info("Received message: " + message);
        template.convertAndSend("/topic/notification",
                                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + " - " + message);
    }
}
