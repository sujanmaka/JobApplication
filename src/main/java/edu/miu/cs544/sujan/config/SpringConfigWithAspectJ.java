package edu.miu.cs544.sujan.config;

import edu.miu.cs544.sujan.aspect.AspectJLogging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
 * We can have single config file instead of multiple for each env
 * */

@Configuration
@EnableAspectJAutoProxy
public class SpringConfigWithAspectJ {

    @Bean
    public AspectJLogging logger() {
        return new AspectJLogging();
    }
}
