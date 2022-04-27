package com.mp.trade.service

import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import com.mp.trade.dto.TradeRequest
import com.mp.trade.repo.TradeRepository
import com.mp.trade.support.Constant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
internal class TradeServiceTest(
) {
    @InjectMocks
    lateinit var tradeService: TradeService
    @Mock
    lateinit var tradeRepository: TradeRepository

    @Test
    fun trade() {
        // given
        val given = TradeRequest(Constant.UUID_FOR_TEST, Trade.TradeType.DEPOSIT, Point(10000))

        Mockito.`when`(tradeRepository.save(any(Trade::class.java)))
            .thenAnswer {invocation ->
                return@thenAnswer invocation.getArgument(0)
            }

        // when
        val tradeResponse = tradeService.openTrade(given)

        // then
        assertAll(
            { assertNotNull(tradeResponse.id) },
            { assertEquals(tradeResponse.type, Trade.TradeType.DEPOSIT) },
            { assertEquals(tradeResponse.payPointId, Constant.UUID_FOR_TEST)},
            { assertEquals(tradeResponse.amount, Point(10000)) }
        )
    }
}