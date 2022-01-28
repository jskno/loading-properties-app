package com.jskno.kafka.loadingpropertiesapp;

import com.jskno.kafka.loadingpropertiesapp.config.AppEnvironmentPreparedListener;
import com.jskno.kafka.loadingpropertiesapp.services.KafkaConsumerService;
import com.jskno.kafka.loadingpropertiesapp.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
public class LoadingPropertiesAppApplication implements ApplicationRunner {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public static void main(String[] args) {

        new SpringApplicationBuilder(LoadingPropertiesAppApplication.class)
                .listeners(new AppEnvironmentPreparedListener())
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        kafkaProducerService.sendMessage("Hi Welcomer to Spring Loading Properties !!!");
    }
}
