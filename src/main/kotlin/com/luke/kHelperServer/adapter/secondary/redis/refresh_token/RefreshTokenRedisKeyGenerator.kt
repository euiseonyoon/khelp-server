package com.luke.kHelperServer.adapter.secondary.redis.refresh_token

import com.luke.kHelperServer.adapter.secondary.redis.shared.RedisKeyGenerator
import org.springframework.stereotype.Component

@Component
class RefreshTokenRedisKeyGenerator : RedisKeyGenerator<Long> {
    override val prefix = "refresh_token:"

    override fun generate(param: Long): String = prefix + param
}
