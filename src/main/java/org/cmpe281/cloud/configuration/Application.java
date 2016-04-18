package org.cmpe281.cloud.configuration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nakul Sharma
 *         Class to configure application context
 *         SpringBootApplication doesn't support base package feature hence below 3 annotations has been used
 */
@Configuration
@ComponentScan(basePackages = "org.cmpe281.cloud")
@EnableAutoConfiguration
public class Application {
    /**
     * Main method for the project.
     *
     * @param args Nothing needs to be passed
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
