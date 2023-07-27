package com.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notification-topic")
    public void handleNotification(OrderPlacedEvent orderDto){
        log.info("Recived notification for order- {}", orderDto.getOrderNumber());
    }
//    @Bean
//    public Consumer<Message<OrderPlacedEvent>> notificationEventSupplier() {
//        return message -> {
//            try {
//                new EmailSender().sendEmail(message.getPayload());
//            } catch (InterruptedException e) {
//                throw new RuntimeException("Something went wrong while sending email");
//            }
//        };
//    }
}
