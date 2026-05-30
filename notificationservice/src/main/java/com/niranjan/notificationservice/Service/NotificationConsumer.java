package com.niranjan.notificationservice.Service;

import com.niranjan.commonlib.Dto.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "order-created-topic",groupId = "notification-group")
    public void ConsumerOrderEvent(OrderCreatedEvent event) {
        System.out.println("Order event received");
        sendemail(event);

    }

    public  void sendemail(OrderCreatedEvent event){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(event.getEmail());
        mailMessage.setSubject("Order Confromation");
        mailMessage.setText("Hello user "+event.getUserId() +",your order " +event.getOrderId()+",for product"+event.getProductName()+
                "has been successfully created");
        mailSender.send(mailMessage);

    }
}
