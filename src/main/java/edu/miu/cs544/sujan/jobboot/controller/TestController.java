package edu.miu.cs544.sujan.jobboot.controller;

import edu.miu.cs544.sujan.jobboot.component.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    Sender sender;

    @GetMapping
    public void sendMessage(String message) {
        sender.send(message);
    }
}
