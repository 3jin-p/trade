package com.mp.trade.dto

import com.mp.trade.domain.Trade
import java.util.*

data class TradeResponse(
    val id: UUID,
    val payPointId: UUID,
    val type: Trade.TradeType,
    val amount: Long,
) {

    companion object {
        fun from(entity: Trade) = TradeResponse(entity.id, entity.payPointId, entity.type, entity.amount.point)
    }
}