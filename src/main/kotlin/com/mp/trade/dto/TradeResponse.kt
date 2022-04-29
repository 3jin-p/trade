package com.mp.trade.dto

import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import java.util.*

data class TradeResponse(
    val id: UUID,
    val payPointId: UUID,
    val type: Trade.TradeType,
    val amount: Long,
) {
}