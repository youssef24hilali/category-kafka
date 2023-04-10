package com.example.categorybackend.consumer;

import com.example.categorybackend.entity.Category;
import com.example.categorybackend.repository.kafka.CategoryKafkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CategoryConsumer {

    @Autowired
    private CategoryKafkaRepository categoryKafkaRepository;

    @KafkaListener(topics = "category-topic", groupId = "category-consumer")
    public void receive(Category category) {
        categoryKafkaRepository.save(category);
    }

}
