package com.mp.trade.service

import com.mp.trade.domain.Trade
import com.mp.trade.dto.TradeRequest
import com.mp.trade.dto.TradeResponse
import com.mp.trade.repo.TradeRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TradeService(
    val tradeRepository: TradeRepository
) {
    @Transactional
    fun trade(request: TradeRequest): TradeResponse {
        val trade = Trade(request.payPointId, request.type, request.amount);
        return TradeResponse.from(tradeRepository.save(trade))
    }
}