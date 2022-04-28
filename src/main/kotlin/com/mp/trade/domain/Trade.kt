package com.mp.trade.domain

import com.mp.trade.event.TradeEvent
import org.springframework.data.domain.AbstractAggregateRoot
import java.util.*
import javax.persistence.*

@Entity
class Trade(
    @Column(columnDefinition = "BINARY(16)")
    val payPointId: UUID,
    @Enumerated(value = EnumType.STRING)
    val type: TradeType,
    @Embedded
    val amount: Point,
): AbstractAggregateRoot<Trade>() {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UUID.randomUUID()

    @Enumerated(value = EnumType.STRING)
    var status: TradeStatus = TradeStatus.IN_PROGRESS

    var failReason: String? = null

    enum class TradeType {
        WITHDRAWL, DEPOSIT
    }

    enum class TradeStatus {
        IN_PROGRESS, SUCCESS, FAIL
    }

    fun success() {
        status = TradeStatus.SUCCESS
    }

    fun fail(reason: String?) {
        status = TradeStatus.FAIL
        this.failReason = reason
    }
}