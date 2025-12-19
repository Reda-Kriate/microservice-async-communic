package org.reda.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.reda.common.event.OrderNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener( topics = "notificationTopic", groupId = "notification-group")
    public void notificationListiner(OrderNumber orderEvent){
        log.info("Notification sent to OrderNumber - {}",orderEvent.getId());
    }

}
