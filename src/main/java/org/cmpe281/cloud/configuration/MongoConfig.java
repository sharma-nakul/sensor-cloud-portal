package org.cmpe281.cloud.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Arrays;

/**
 * Created by Naks on 17-Apr-16.
 * Configuration file for Mongo DB
 */

@Configuration
@PropertySource(value = {"classpath:mongo.properties"})
public class MongoConfig {

    @Autowired
    Environment env;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {

        // Set credentials
        MongoCredential credential = MongoCredential.createCredential(
                env.getRequiredProperty("mongo.dbuser"),
                env.getRequiredProperty("mongo.dbname"),
                env.getRequiredProperty("mongo.dbpassword").toCharArray());
        ServerAddress serverAddress = new ServerAddress(
                env.getRequiredProperty("mongo.server"),
                env.getRequiredProperty("mongo.port", Integer.class));

        // Mongo Client
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));

        // Mongo DB Factory
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
                mongoClient, env.getRequiredProperty("mongo.dbname"));

        return simpleMongoDbFactory;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public MongoOperations mongoOperations() throws Exception {
        return mongoTemplate();
    }
}
