package com.luke.kHelperServer.adapter.secondary.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfiguration(
    @Value("\${spring.data.redis.host}")
    val host: String,
    @Value("\${spring.data.redis.port}")
    val port: Int,
    @Value("\${spring.data.redis.password}")
    val password: String,
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration(host, port)
        if (password.trim().isNotEmpty()) {
            config.setPassword(password)
        }

        val factory = LettuceConnectionFactory(config)
        factory.afterPropertiesSet()

        // 실제 연결 테스트: 실패 시 예외 발생
        factory.connection.use { it.ping() }

        return factory
    }

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, ByteArray> {
        val template = RedisTemplate<String, ByteArray>()
        template.connectionFactory = connectionFactory

        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = RedisSerializer.byteArray()

        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = RedisSerializer.byteArray()

        template.afterPropertiesSet()
        return template
    }
}
