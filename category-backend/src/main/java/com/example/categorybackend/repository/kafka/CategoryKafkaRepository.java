package com.example.categorybackend.repository.kafka;

import com.example.categorybackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryKafkaRepository extends JpaRepository<Category, Long> {
}
