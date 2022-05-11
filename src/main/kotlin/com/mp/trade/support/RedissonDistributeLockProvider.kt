package com.mp.trade.support

import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedissonDistributeLockProvider(
    val redisson: RedissonClient
): DistributedLockProvider {

    override fun <T> lock(lockId: String, lockWaitSeconds: Long, lockTtl: Long, operation: () -> T): T {
        val lock = redisson.getLock(lockId)

        try {
            val isLocked = lock.tryLock(lockWaitSeconds, lockTtl, TimeUnit.SECONDS)

            if (!isLocked) {
                throw IllegalAccessException("남은 락 타임: ${lock.remainTimeToLive()}")
            }

            return operation.invoke()

        } catch (e: InterruptedException) {
            throw e // custom exception or handling error
        } finally {
            lock.unlock()
        }
    }

}