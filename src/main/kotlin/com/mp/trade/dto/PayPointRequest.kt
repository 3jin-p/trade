package com.mp.trade.dto

import com.mp.trade.domain.Point
import java.util.UUID

class PayPointRequest {

    data class DepositRequest(
        val tradeId: UUID,
        val payPointId: UUID,
        val amount: Point
    )
}