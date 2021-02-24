package com.example.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
class KafkaListenersExample {
    @Autowired
    private CustomerRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(KafkaListenersExample.class);

    @KafkaListener(topics = "customerdata.customer")
    void listener(String data) throws JsonProcessingException {
        System.out.println(data);
        ObjectMapper obm=new ObjectMapper();
        Root root=obm.readValue(data,Root.class);

        Customer cust=repository.findById(root.fullDocument._id.oid.toString()).orElse(null);
        if(cust!=null) {
            cust.status=true;
            repository.save(cust);
        }

        //logger.info(data);
    }
/*
    @KafkaListener(
            topics = "reflectoring-1, reflectoring-2",
            groupId = "reflectoring-group-2")
    void commonListenerForMultipleTopics(String message) {
        System.out.println("MultipleTopicListener - {}\", message");
        logger.info("MultipleTopicListener - {}", message);
    }

 */
}

final class Id{
    public String _data;
    @JsonProperty("$oid")
    public String oid;
}

final class Timestamp{
    public int t;
    public int i;
}

final class ClusterTime{
    @JsonProperty("$timestamp")
    public Timestamp timestamp;
}

final class FullDocument{
    public Id _id;
    public String firstName;
    public String lastName;
    public String status;
    public String _class;
}

final class Ns{
    public String db;
    public String coll;
}

final class DocumentKey{
    public Id _id;
}

final class Root{
    public Id _id;
    public String operationType;
    public ClusterTime clusterTime;
    public FullDocument fullDocument;
    public Ns ns;
    public DocumentKey documentKey;
}


