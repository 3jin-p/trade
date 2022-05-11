package com.mp.trade.service

import com.mp.trade.dto.TradeRequest
import com.mp.trade.dto.TradeResponse
import com.mp.trade.support.DistributedLockProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class TradeApplicationService(
    val tradeService: TradeService,
    val redissonDistributeLockProvider: DistributedLockProvider,
    val transactionTemplate: TransactionTemplate
) {
    companion object {
        const val TRADE_LOCK_TTL = 2L
        const val TRADE_LOCK_WAITS = 3L
    }

    fun openTrade(request: TradeRequest): TradeResponse? {
        return redissonDistributeLockProvider.lock(request.payPointId.toString(), TRADE_LOCK_WAITS, TRADE_LOCK_TTL) {
            transactionTemplate.execute { tradeService.openTrade(request) }
        }
    }
}