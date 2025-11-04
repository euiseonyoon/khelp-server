package com.luke.kHelperServer.adapter.secondary.redis.refresh_token

import com.luke.kHelperServer.adapter.secondary.redis.shared.GenericRedisRepository
import com.luke.kHelperServer.application.auth.login.required_port.RefreshTokenRepository
import com.luke.kHelperServer.domain.login.HashedRefreshToken
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import kotlin.time.Duration.Companion.microseconds

@Repository
class RefreshTokenRedisRepository(
    redisTemplate: RedisTemplate<String, ByteArray>,
    redisKeyGenerator: RefreshTokenRedisKeyGenerator,
    @Value("\${jwt.refresh-duration-ms}")
    val refreshDurationMs: Long,
) : GenericRedisRepository<Long, HashedRefreshToken>(
    redisTemplate = redisTemplate,
    redisKeyGenerator = redisKeyGenerator,
    valueSerializer = HashedRefreshToken.serializer(),
    defaultTtl = refreshDurationMs.microseconds,
), RefreshTokenRepository {
    override fun saveRefreshToken(accountId: Long, hashedRefreshToken: HashedRefreshToken) {
        super.save(accountId, hashedRefreshToken, null)
    }
}
