package org.matera.fff

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton

@Factory
class MongoDbFactory {

    @Singleton
    @Bean
    MongoClient mongoClient(String connectionString) {
        MongoClients.create(connectionString)
    }
}
