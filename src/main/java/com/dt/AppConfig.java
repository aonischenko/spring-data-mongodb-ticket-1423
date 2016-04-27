package com.dt;

import com.dt.spring.CustomConverters;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

@Configuration
public class AppConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("mongo.database");
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoClientURI mongoUri = new MongoClientURI(environment.getProperty("mongo.uri"));
        return new MongoClient(mongoUri);
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(CustomConverters.getConvertersToRegister());
    }
}
