package com.mp.trade.service

import com.mp.trade.support.RedissonDistributeLockProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.IllegalArgumentException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock

@SpringBootTest
internal class RedissonDistributeLockProviderTest(
    @Autowired
    val redissonDistributeLockProvider: RedissonDistributeLockProvider
) {


    @Test
    fun testLock() {
        //given
        val lockId = "lockId"
        val lockWaitSeconds = 1L
        val lockTtl = 100L

        val number = Number(0)

        //when
        val executors = Executors.newFixedThreadPool(10)

        for (i in 1 .. 100) {
           executors.execute { redissonDistributeLockProvider.lock(lockId, lockWaitSeconds, lockTtl) { number.plus() } }
        }

        executors.shutdown();
        executors.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        // then
        assertThat(number.number).isEqualTo(100)
    }


    private class Number(
        var number: Int
    ) {

        fun plus() {
            this.number += 1
        }

    }
}