package org.reda.notificationservice.listiner;

import lombok.extern.slf4j.Slf4j;
import org.reda.common.event.OrderNumber;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class NotificationListiner {
    @KafkaListener( topics = "notificationTopic", groupId = "notification-group" )
    public void notificationListiner(OrderNumber orderEvent){
        log.info("notification sent to OrderNumber - {}",orderEvent.getId());
    }
}
