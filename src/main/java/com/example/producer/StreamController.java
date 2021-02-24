package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


@RestController
public class StreamController {
    private static final Logger logger = LoggerFactory.getLogger(StreamController.class);
    @Autowired
    private CustomerRepository repository;
    @RequestMapping("/")
    public String index() {
        logger.info("Kumar  Greetings from Spring Boot");
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/getall")
    public String getAll() {
        logger.info("Kumar  Greetings from Spring Boot");
        //repository.deleteAll();
        StringBuilder sb=new StringBuilder();
        // save a couple of customers
        Random random =new Random();
        Integer sub=random.nextInt(1000);
        String finalv=sub.toString();
        repository.save(new Customer("Rishi-" +finalv, "Kumar-"+finalv));

        return finalv;
    }

}