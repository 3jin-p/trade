package com.mp.trade.event

import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import java.util.UUID

class TradeEvent {

    data class TradeOpenEvent(
        val id: UUID,
        val pointId: UUID,
        val type: Trade.TradeType,
        val amount: Point
    )
}