package com.mp.trade.domain

import com.mp.trade.support.Constant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TradeTest {

    val depositTrade = Trade(Constant.UUID_FOR_TEST, Trade.TradeType.DEPOSIT, Point(10000))

    @Test
    fun success() {
        depositTrade.success()
        assertThat(depositTrade.status).isEqualTo(Trade.TradeStatus.SUCCESS)
    }

    @Test
    fun fail() {
        depositTrade.fail("카드사 점검")
        assertThat(depositTrade.status).isEqualTo(Trade.TradeStatus.FAIL)
        assertThat(depositTrade.failReason).isEqualTo("카드사 점검")
    }
}