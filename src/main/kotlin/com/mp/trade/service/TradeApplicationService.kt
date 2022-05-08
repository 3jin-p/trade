package com.mp.trade.service

import com.mp.trade.dto.TradeRequest
import com.mp.trade.dto.TradeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class TradeApplicationService(
    val tradeService: TradeService,
    val tradeTuner: TradeTuner,
    val transactionTemplate: TransactionTemplate
) {
    companion object {
        const val TRADE_REQUEST_APPROVE_TERM = 30L
    }

    fun openTrade(request: TradeRequest): TradeResponse? {
        tradeTuner.tune(request.payPointId, TRADE_REQUEST_APPROVE_TERM)
        return transactionTemplate.execute { tradeService.openTrade(request) }
    }
}