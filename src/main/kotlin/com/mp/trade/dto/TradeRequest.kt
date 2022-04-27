package com.mp.trade.dto

import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import java.util.UUID

data class TradeRequest(
    val payPointId: UUID,
    val type: Trade.TradeType,
    val amount: Point,
) {
}