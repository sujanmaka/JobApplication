package edu.miu.cs544.sujan.jobboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private MessageSource messageSource;

//    @Autowired
//    Sender sender;

//    @GetMapping
//    public void sendMessage(String message) {
//        sender.send(message);
//    }

    @GetMapping(path = "/hello")
    public String helloWorld(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
