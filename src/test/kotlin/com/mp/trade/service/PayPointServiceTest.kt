package com.mp.trade.service

import com.mp.trade.domain.PayPoint
import com.mp.trade.domain.Point
import com.mp.trade.domain.Trade
import com.mp.trade.dto.PayPointRequest
import com.mp.trade.event.PayPointEvent
import com.mp.trade.repo.PayPointRepository
import com.mp.trade.repo.TradeRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ExtendWith(value = [MockitoExtension::class])
internal class PayPointServiceTest {
    @InjectMocks
    lateinit var payPointService: PayPointService
    @Mock
    lateinit var payPointRepository: PayPointRepository
    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher

    @Test
    fun deposit() {
        val tradeId = UUID.fromString("a459313e-c610-11ec-9d64-0242ac120002")
        val payPointId = UUID.fromString("c0ecc950-c610-11ec-9d64-0242ac120002")

        val payPoint = PayPoint(Point(3500))
        payPoint.id = payPointId

        Mockito.`when`(payPointRepository.findById(payPointId)).thenReturn(Optional.of(payPoint))

        // given
        val request = PayPointRequest.DepositRequest(tradeId, payPointId, Point(10000))

        //when
        payPointService.deposit(request)

        //then
        assertThat(payPoint.amount).isEqualTo(Point(13500))
        Mockito.verify(eventPublisher).publishEvent(PayPointEvent.PayDepositProcessEvent(payPointId, tradeId, true))
    }

    @Test
    fun depositFail() {
        val tradeId = UUID.fromString("a459313e-c610-11ec-9d64-0242ac120002")
        val payPointId = UUID.fromString("c0ecc950-c610-11ec-9d64-0242ac120002")

        val payPoint = PayPoint(Point(3500))
        payPoint.id = payPointId

        // fail factor
        Mockito.`when`(payPointRepository.findById(payPointId)).thenReturn(null)

        // given
        val request = PayPointRequest.DepositRequest(tradeId, payPointId, Point(10000))

        //when
        payPointService.deposit(request)

        //then
        assertThat(payPoint.amount).isEqualTo(Point(3500))
        Mockito.verify(eventPublisher).publishEvent(PayPointEvent.PayDepositProcessEvent(payPointId, tradeId, false))
    }
}