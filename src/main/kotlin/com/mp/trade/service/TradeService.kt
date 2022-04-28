package com.mp.trade.service

import com.mp.trade.domain.Trade
import com.mp.trade.dto.TradeRequest
import com.mp.trade.dto.TradeResponse
import com.mp.trade.event.TradeEvent
import com.mp.trade.exception.EntityNotFoundException
import com.mp.trade.repo.TradeRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class TradeService(
    val tradeRepository: TradeRepository,
    val eventPublisher: ApplicationEventPublisher
) {
    @Transactional
    fun openTrade(request: TradeRequest): TradeResponse {
        val trade = Trade(request.payPointId, request.type, request.amount)
        eventPublisher.publishEvent(TradeEvent.TradeOpenEvent(trade.id, trade.payPointId, trade.type, trade.amount))
        return TradeResponse.from(tradeRepository.save(trade))
    }

    fun processResult(tradeId: UUID, result: Boolean) {
        val trade = tradeRepository.findById(tradeId).orElseThrow { EntityNotFoundException() }

        if (result) {
            trade.success()
        } else {
            trade.fail()
        }
    }
}