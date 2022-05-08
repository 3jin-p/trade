package com.mp.trade.config

import io.lettuce.core.dynamic.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import java.util.*

@Configuration
@EnableRedisRepositories
class RedisConfig(
    val redisProperties: RedisProperties
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val lettuceConnectionFactory = LettuceConnectionFactory(redisProperties.host, redisProperties.port);
        return lettuceConnectionFactory;
    }

    @Bean
    fun redisTemplate(): RedisTemplate<UUID, Any>  {
        val redisTemplate: RedisTemplate<UUID, Any> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}