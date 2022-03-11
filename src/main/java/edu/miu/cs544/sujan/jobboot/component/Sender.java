package edu.miu.cs544.sujan.jobboot.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Value("${jms.brokerQueue}")
    private String broker;

    @Autowired
    JmsTemplate jmsTemplate;

    public void send(String message) {
        jmsTemplate.convertAndSend(broker, message);
    }
}
