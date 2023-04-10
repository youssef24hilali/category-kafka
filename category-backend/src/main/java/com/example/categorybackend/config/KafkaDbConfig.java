package com.example.categorybackend.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "kafkaEntityManagerFactory",
        transactionManagerRef = "kafkaTransactionManager",
        basePackages = { "com.example.categorybackend.repository.kafka" }
)
public class KafkaDbConfig {

    @Bean(name = "kafkaDataSource")
    @ConfigurationProperties(prefix = "kafka.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "kafkaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    kafkaEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("kafkaDataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.example.categorybackend.entity")
                        .persistenceUnit("kafka")
                        .build();
    }
    @Bean(name = "kafkaTransactionManager")
    public PlatformTransactionManager kafkaTransactionManager(
            @Qualifier("kafkaEntityManagerFactory") EntityManagerFactory
                    kafkaEntityManagerFactory
    ) {
        return new JpaTransactionManager(kafkaEntityManagerFactory);
    }

}
