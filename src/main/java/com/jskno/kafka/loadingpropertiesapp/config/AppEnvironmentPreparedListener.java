package com.jskno.kafka.loadingpropertiesapp.config;


import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Properties;

public class AppEnvironmentPreparedListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        ConfigurableEnvironment environment = event.getEnvironment();
        String consumerServer = environment.getProperty("spring.kafka.consumer.bootstrap-servers");
        if(consumerServer.equals("undefined")) {
            Properties props = new Properties();
            props.setProperty("spring.kafka.consumer.bootstrap-servers", "localhost:2181");
            environment.getPropertySources().addFirst(
                    new PropertiesPropertySource("kafakConsumerOnTheFly", props));
        }
    }
}
