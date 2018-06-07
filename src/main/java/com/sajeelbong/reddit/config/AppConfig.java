package com.sajeelbong.reddit.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    MongoOperations mongoOperations(){
      MongoClient mongoClient = new MongoClient();
      MongoOperations mongoOperations = new MongoTemplate(mongoClient, "reddit_starter");
      return mongoOperations;
    }
}

