package com.mp.trade.support

import org.springframework.stereotype.Component

@Component
interface DistributedLockProvider {
    fun <T> lock(lockId: String, lockWaitSeconds: Long, lockTtl: Long, operation: () -> T): T
}