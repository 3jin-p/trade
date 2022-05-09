package com.mp.trade.integration

import com.mp.trade.domain.PayPoint
import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import com.mp.trade.dto.TradeRequest
import com.mp.trade.repo.PayPointRepository
import com.mp.trade.repo.TradeRepository
import com.mp.trade.service.TradeApplicationService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import java.util.concurrent.Executors

@SpringBootTest
class TradeConcurrencyTest {
    @Autowired
    lateinit var tradeService: TradeApplicationService
    @Autowired
    lateinit var payPointRepository: PayPointRepository
    @Autowired
    lateinit var tradeRepository: TradeRepository

    lateinit var payPointId: UUID

    @BeforeEach
    fun initData() {
        val payPoint = PayPoint()
        payPointRepository.save(payPoint)
        payPointId = payPoint.id
    }

    @AfterEach
    fun removeData() {
        payPointRepository.deleteAll()
        tradeRepository.deleteAll()
    }

    @Test
    fun testDeposit() {
        val executorService = Executors.newFixedThreadPool(10)

        for (i in 1 .. 10) {
            executorService.execute {
                tradeService.openTrade(TradeRequest(payPointId, Trade.TradeType.DEPOSIT, Point(1000))) }
        }

        Thread.sleep(3000) // 비동기 event 처리 대기
        val result = payPointRepository.findById(payPointId).orElseThrow { RuntimeException() }
        assertThat(result.amount).isEqualTo(Point(10000))

        val trades = tradeRepository.findAll()
        assertTrue(trades.stream().allMatch { t -> t.status == Trade.TradeStatus.SUCCESS })
    }
}