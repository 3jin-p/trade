package com.mp.trade.service

import com.mp.trade.domain.Trade
import com.mp.trade.dto.PayPointRequest
import com.mp.trade.event.TradeEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class TradeEventAdaptor(
    val payPointService: PayPointService
) {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun listen(event: TradeEvent.TradeOpenEvent) {
        if (event.type == Trade.TradeType.DEPOSIT) {
            payPointService.deposit(PayPointRequest.DepositRequest(event.id, event.pointId, event.amount))
        } else if (event.type == Trade.TradeType.WITHDRAWL) {
            payPointService.withdrawal(PayPointRequest.WithdrawalRequest(event.id, event.pointId, event.amount))
        }
    }
}