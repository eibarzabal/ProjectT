package se.myapp.greeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.myapp.greeting.service.GreetingService;

@Configuration
public class GreetingConfiguration {

    @Bean
    public GreetingService getGreetingService() {
        return new GreetingService();
    }

}
