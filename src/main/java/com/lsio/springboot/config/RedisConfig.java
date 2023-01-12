package com.lsio.springboot.config;

// import io.lettuce.core.RedisClient;
// import io.lettuce.core.RedisURI;
// import io.lettuce.core.api.StatefulRedisConnection;
// import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
// @EnableRedisRepositories
public class RedisConfig {

    // @Value("${spring.redis.url}")
    // private String uri;

    // @Bean
    // public RedisCommands connectionFactory() {
    //     RedisURI redisURI = RedisURI.create(uri);
    //     RedisClient redisClient = RedisClient.create(redisURI);
    //     StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
    //     return redisConnection.sync();
    // }
}
