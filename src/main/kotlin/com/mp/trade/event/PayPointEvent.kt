package com.mp.trade.event

import com.mp.trade.domain.Point
import java.util.UUID

class PayPointEvent {

    data class PayDepositProcessEvent(
        val payPointId: UUID,
        val tradeId: UUID,
        val success: Boolean,
    )
}