package com.lsio.springboot.config;

import java.util.UUID;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Duration;
@Configuration
public class RedisConfig {

	
		@Value(("${spring.redis.host}"))
		private String redisHost;
	
		@Value(("${spring.redis.port}"))
		private int redisPort;
	
		@Bean
		public JedisConnectionFactory jedisConnectionFactory(){
			RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
			configuration.setHostName(redisHost);
			configuration.setPort(redisPort);
			JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
			builder.connectTimeout(Duration.ofMinutes(1));
			return new JedisConnectionFactory(configuration, builder.build());
		}
	
		@Bean
		public RedisTemplate<String, Object> redisTemplate(){
			RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
			redisTemplate.setConnectionFactory(jedisConnectionFactory());
			redisTemplate.setKeySerializer(this.jsonRedisSerializer());
			redisTemplate.setValueSerializer(this.jsonRedisSerializer());
			redisTemplate.setHashValueSerializer(this.jsonRedisSerializer());
			return redisTemplate;
		}
	
		protected Jackson2JsonRedisSerializer<Object> jsonRedisSerializer() {
			Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
			objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
					ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
			jsonRedisSerializer.setObjectMapper(objectMapper);
			return jsonRedisSerializer;
		}
}
