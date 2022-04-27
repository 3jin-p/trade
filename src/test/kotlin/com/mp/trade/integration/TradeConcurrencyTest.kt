package com.mp.trade.integration

import com.mp.trade.domain.PayPoint
import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import com.mp.trade.dto.PayPointRequest
import com.mp.trade.dto.TradeRequest
import com.mp.trade.repo.PayPointRepository
import com.mp.trade.repo.TradeRepository
import com.mp.trade.service.PayPointService
import com.mp.trade.service.TradeService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.RuntimeException
import java.util.*
import java.util.concurrent.Executors

@SpringBootTest
class TradeConcurrencyTest {
    @Autowired
    lateinit var tradeService: TradeService
    @Autowired
    lateinit var payPointService: PayPointService
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

    @Test
    fun testDeposit() {
        val executorService = Executors.newFixedThreadPool(10)

        for (i in 1 .. 10) {
            executorService.execute {
                tradeService.openTrade(TradeRequest(payPointId, Trade.TradeType.DEPOSIT, Point(1000))) }
        }

        // 이벤트까지 처리되는건 테스트안된다. 다른방법쓰기
        val result = payPointRepository.findById(payPointId).orElseThrow { RuntimeException() }
        assertThat(result.amount).isEqualTo(10000)
    }

    @Test
    fun testDeposit2() {
        val executorService = Executors.newFixedThreadPool(10)

        for (i in 1 .. 10) {
            // trade init Open Event 발생안시킬시 통과
            val trade = tradeRepository.save(Trade(payPointId, Trade.TradeType.DEPOSIT, Point(1000)))

            executorService.execute {
                payPointService.deposit(PayPointRequest.DepositRequest(trade.id, payPointId, Point(1000))) }
        }

        val result = payPointRepository.findById(payPointId).orElseThrow { RuntimeException() }
        assertThat(result.amount).isEqualTo(10000)
    }
}