package com.mp.trade.service

import com.mp.trade.event.PayPointEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class PayPointEventAdaptor(
    val tradeService: TradeService
) {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun listen(event: PayPointEvent.PayDepositProcessEvent) {
        tradeService.processResult(event.tradeId, event.success)
    }
}