package com.mp.trade.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PayPoint(
    @Embedded
    val amount: Point
) {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UUID.randomUUID()

    fun addPoint(point: Point) {
        amount.add(point)
    }

    fun minusPoint(point: Point) {
        amount.minus(point)
    }

}