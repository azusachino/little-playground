package cn.az.webflux.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;

/**
 * TODO
 *
 * @author az
 * @since 2021/8/2 23:05
 */
@Configuration
@EnableMongoRepositories(basePackages = "cn.az.webflux.mongo.repo")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${mongo.dbName}")
    private String dbName;

    @Override
    @NonNull
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    @NonNull
    protected String getDatabaseName() {
        return dbName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}
