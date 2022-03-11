package edu.miu.cs544.sujan.jobboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JobBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobBootApplication.class, args);
    }

}
