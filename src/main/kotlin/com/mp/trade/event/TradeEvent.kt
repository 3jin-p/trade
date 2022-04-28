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
    ) {
        companion object {
            fun from(entity: Trade): TradeOpenEvent {
                return TradeOpenEvent(entity.id, entity.payPointId, entity.type, entity.amount)
            }
        }
    }


    data class TradeClosedEvent(
        val id: UUID,
        val pointId: UUID,
        val type: Trade.TradeType,
        val amount: Point,
        val status: Trade.TradeStatus,
        val failReason: String?
    ) {
        companion object {
            fun from(entity: Trade): TradeClosedEvent {
                return TradeClosedEvent(entity.id, entity.payPointId, entity.type, entity.amount, entity.status, entity.failReason)
            }
        }
    }
}