package com.mp.trade.event

import java.util.UUID

class PayPointEvent {

    data class PayDepositProcessEvent(
        val payPointId: UUID,
        val tradeId: UUID,
        val success: Boolean,
        val reason: String? = null
    ) {

        companion object {
            fun success(payPointId: UUID, tradeId: UUID): PayDepositProcessEvent {
                return PayDepositProcessEvent(payPointId, tradeId, true)
            }

            fun fail(payPointId: UUID, tradeId: UUID, reason: String?): PayDepositProcessEvent {
                return PayDepositProcessEvent(payPointId, tradeId, false, reason)
            }
        }
    }
}