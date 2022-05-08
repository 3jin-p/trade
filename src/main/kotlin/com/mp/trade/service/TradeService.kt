package com.mp.trade.service

import com.mp.trade.domain.Trade
import com.mp.trade.dto.TradeRequest
import com.mp.trade.dto.TradeResponse
import com.mp.trade.event.TradeEvent
import com.mp.trade.exception.EntityNotFoundException
import com.mp.trade.repo.TradeRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TradeService(
    val tradeTuner: TradeTuner,
    val tradeRepository: TradeRepository,
    val eventPublisher: ApplicationEventPublisher
) {
    @Transactional
    fun openTrade(request: TradeRequest): TradeResponse {
        tradeTuner.tune(request.payPointId, 30)
        val trade = Trade(request.payPointId, request.type, request.amount)
        eventPublisher.publishEvent(TradeEvent.TradeOpenEvent.from(trade))
        tradeRepository.save(trade)
        return TradeResponse.from(trade)
    }

    fun processResult(tradeId: UUID, result: Boolean, failReason: String?) {
        val trade = tradeRepository.findByIdOrNull(tradeId) ?: throw EntityNotFoundException()

        if (result) {
            trade.success()
        } else {
            trade.fail(failReason)
        }
        eventPublisher.publishEvent(TradeEvent.TradeClosedEvent.from(trade)) // ToDo 받아서 처리결과 클라이언트에 푸시. -> 클라이언트에서 PayPoint 조회.
    }
}