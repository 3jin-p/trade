package com.mp.trade.dto

import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import java.util.*

data class TradeResponse(
    val id: UUID,
    val payPointId: UUID,
    val type: Trade.TradeType,
    val amount: Point,
) {

    companion object {
        fun from(entity: Trade): TradeResponse {
            return TradeResponse(entity.id, entity.payPointId, entity.type, entity.amount)
        }
    }
}