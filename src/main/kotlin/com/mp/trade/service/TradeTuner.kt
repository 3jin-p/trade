package com.mp.trade.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class TradeTuner(
    val redisTemplate: RedisTemplate<UUID, Any>
) {
    companion object {
        const val KEY_NON_EXISTS = -2L;
    }

    fun tune(payPointId: UUID, keyExpireSeconds: Long) {
        val ttl = redisTemplate.getExpire(payPointId)
        if (ttl != KEY_NON_EXISTS) {
            throw IllegalArgumentException("$ttl 초후 시도해주세요")
        }
        redisTemplate.opsForValue().set(payPointId, 1)
        redisTemplate.expire(payPointId, keyExpireSeconds, TimeUnit.SECONDS)
    }

}