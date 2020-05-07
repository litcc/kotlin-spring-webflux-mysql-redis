package me.lovol.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext

@Configuration(value = "redisConfiguration")
class RedisConfiguration {

    @Bean("redisTemplate")
    internal fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<*, *> {
        //return ReactiveRedisTemplate<*,*>(factory)
        return ReactiveRedisTemplate(factory, RedisSerializationContext.string())
    }
}