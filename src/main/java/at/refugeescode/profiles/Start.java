package at.refugeescode.profiles;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Start {
    @Bean
    ApplicationRunner applicationRunner(){
     return args -> {
         System.out.println(sayHello());
     };
    }
    public String sayHello(){
        return "hello";
    }
}
