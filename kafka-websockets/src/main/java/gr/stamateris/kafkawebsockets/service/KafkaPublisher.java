package gr.stamateris.kafkawebsockets.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaPublisher {

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("Topic1", message);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.warn("Unable to send message=[" + message + "] due to error: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("Sent message=[" + message + "] with offset=[" + stringStringSendResult.getRecordMetadata()
                        .offset() + "]");
            }
        });
    }
}
