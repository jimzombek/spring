package com.jmzombe.configurationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * This is the main entry point and configuration for the Configuration Service application.
 * annotated with {@link SpringBootApplication} to enable configuration, classpath component 
 * scanning, etc.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigurationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServiceApplication.class, args);
    }
}
   
  