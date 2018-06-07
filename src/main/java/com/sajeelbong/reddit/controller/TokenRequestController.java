package com.sajeelbong.reddit.controller;

import com.sajeelbong.reddit.entity.Customer;
import com.sajeelbong.reddit.service.TokenFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TokenRequestController {

    @Autowired
    private TokenFetcherService tokenFetcherService;

    @Autowired
    private MongoOperations mongoOperations;

    @GetMapping("/token")
    public void getToken(){
        System.out.println("Recieved request");
        tokenFetcherService.generateAccessToken();
    }

    @GetMapping("/hit")
    public void hit(){
        Customer customer = new Customer("saj", "bong");
        try {
            if (mongoOperations.collectionExists("customer")){
                mongoOperations.insert(customer);
                System.out.println("***********************/n/n/n");
            }
;
        } catch (Exception e) {
            System.out.println("Caught exception");
        }
    }



}

