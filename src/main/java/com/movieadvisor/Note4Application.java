package com.movieadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Note4Application {

    @Bean
    public RestTemplate getForYou(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Note4Application.class, args);
    }

}
