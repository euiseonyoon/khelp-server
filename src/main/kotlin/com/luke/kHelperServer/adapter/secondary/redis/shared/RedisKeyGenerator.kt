package com.luke.kHelperServer.adapter.secondary.redis.shared

interface RedisKeyGenerator<P> {
    val prefix: String
    fun generate(param: P): String
}
