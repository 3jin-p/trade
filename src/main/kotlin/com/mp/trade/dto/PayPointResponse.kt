package com.mp.trade.dto

import com.mp.trade.domain.PayPoint
import java.util.*

data class PayPointResponse(
    val id: UUID,
    val amount: Long
) {
    companion object {
        fun from(entity: PayPoint) = PayPointResponse(entity.id, entity.amount.point)
    }
}