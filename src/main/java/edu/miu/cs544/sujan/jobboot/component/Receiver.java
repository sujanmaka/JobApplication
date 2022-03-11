package edu.miu.cs544.sujan.jobboot.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @JmsListener(destination = "${jms.brokerQueue}")
    public void receive1(String message) {
        System.out.println("Received 1 : " + message);
    }

    @JmsListener(destination = "${jms.brokerQueue}")
    public void receive2(String message) {
        System.out.println("Received 2 : " + message);
    }
}
