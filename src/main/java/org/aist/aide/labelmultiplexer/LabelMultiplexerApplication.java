package org.aist.aide.labelmultiplexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCircuitBreaker
@EnableJpaAuditing
@EnableDiscoveryClient
public class LabelMultiplexerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabelMultiplexerApplication.class, args);
    }
}
