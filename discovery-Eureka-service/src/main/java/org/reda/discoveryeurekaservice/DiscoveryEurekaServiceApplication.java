package org.reda.discoveryeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEurekaServiceApplication.class, args);
    }
}
