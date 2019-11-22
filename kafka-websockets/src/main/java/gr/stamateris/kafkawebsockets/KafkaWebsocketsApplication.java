package gr.stamateris.kafkawebsockets;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaWebsocketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaWebsocketsApplication.class, args);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("Topic1", 3, (short) 2);
    }
}
