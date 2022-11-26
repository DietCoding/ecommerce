package com.example.userservice.service;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.example.userservice.jpa.CatalogCount;
import com.example.userservice.jpa.CatalogCountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    CatalogCountRepository repository;

    @Autowired
    public KafkaConsumer(CatalogCountRepository repository){
        this.repository = repository;
    }

    @KafkaListener(topics = "example-order-topic")
    public void processMessage(String kafkaMessage){
        log.info("Kafka Message: ====> " + kafkaMessage);

        Map<Object,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
            }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        CatalogCount catalogCount = repository.findByProductId((String)map.get("productId"));
        catalogCount.setCount((Integer)map.get("qty"));

        repository.save(catalogCount);
    }
}
