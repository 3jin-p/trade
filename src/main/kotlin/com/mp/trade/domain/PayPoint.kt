package com.mp.trade.domain

import com.mp.trade.event.PayPointEvent
import org.springframework.data.domain.AbstractAggregateRoot
import java.util.*
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PayPoint(
    @Embedded
    var amount: Point
): AbstractAggregateRoot<PayPoint>() {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UUID.randomUUID()

    fun addPoint(point: Point) {
        amount = amount.add(point)
    }

    fun minusPoint(tradeId: UUID, point: Point) {
        amount.minus(point)
    }

}