package com.example.categorybackend.producer;

import com.example.categorybackend.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryProducer {

    private static final String TOPIC_NAME = "category-topic";

    @Autowired
    private KafkaTemplate<String, Category> kafkaTemplate;

    public void send(Category category) {
        kafkaTemplate.send(TOPIC_NAME, category);
    }

}
