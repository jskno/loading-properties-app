package com.jskno.kafka.loadingpropertiesapp.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
@Getter
public class KafkaProperties {

    private final String producerServers;
    private final String consumerGroupId;
    private final String consumerServers;

    public KafkaProperties(
            @Value("${spring.kafka.producer.bootstrap-servers}") String producerServers,
            @Value("${kafka.consumer.prefix}") String groupIdPrefix,
            @Value("${spring.kafka.consumer.group-id}") String kafkaGroupId,
            @Value("${spring.kafka.consumer.bootstrap-servers}") String consumerServers) {

        this.producerServers = producerServers;
        this.consumerGroupId = buildConsumerGroupId(groupIdPrefix, kafkaGroupId);
        this.consumerServers = consumerServers;
    }

    private String buildConsumerGroupId(String groupIdPrefix, String kafkaGroupId) {
        if(StringUtils.hasLength(kafkaGroupId)) {
            return groupIdPrefix + kafkaGroupId;
        } else {
            return groupIdPrefix + UUID.randomUUID();
        }
    }
}
