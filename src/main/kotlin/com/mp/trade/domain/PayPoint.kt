package com.mp.trade.domain

import org.springframework.data.domain.AbstractAggregateRoot
import java.util.*
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PayPoint(
    @Embedded
    var amount: Point = Point(0)
): AbstractAggregateRoot<PayPoint>() {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UUID.randomUUID()

    fun addPoint(point: Point) {
        amount = amount.add(point)
    }

    fun minusPoint(point: Point) {
        if (amount.lessThan(point)) {
            throw IllegalArgumentException("포인트가 부족합니다.")
        }
        amount = amount.minus(point)
    }

}