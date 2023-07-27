package com.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSender {

    public void sendEmail(OrderPlacedEvent orderPlacedEvent) throws InterruptedException {
        log.info("Sending Email Confirmation for Order - {}", orderPlacedEvent.getOrderNumber());
        Thread.sleep(100);
        log.info("Email Sent!!");
    }
}
