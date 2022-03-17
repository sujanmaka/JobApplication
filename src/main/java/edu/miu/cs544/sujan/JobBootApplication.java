package edu.miu.cs544.sujan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JobBootApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(JobBootApplication.class, args);
//    }

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(JobBootApplication.class, args);
    }

    public static void restart(String env) {

        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(JobBootApplication.class, "--spring.profiles.active=" + env);
        });

        thread.setDaemon(false);
        thread.start();
    }
}
