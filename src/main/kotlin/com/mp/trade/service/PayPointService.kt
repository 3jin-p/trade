package com.mp.trade.service

import com.mp.trade.dto.PayPointRequest
import com.mp.trade.dto.PayPointResponse
import com.mp.trade.event.PayPointEvent
import com.mp.trade.exception.EntityNotFoundException
import com.mp.trade.repo.PayPointRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PayPointService(
    val payPointRepository: PayPointRepository,
    val eventPublisher: ApplicationEventPublisher
) {

    @Transactional(readOnly = true)
    fun getPayPoint(payPointId: UUID): PayPointResponse {
        val payPoint = payPointRepository.findById(payPointId).orElseThrow { EntityNotFoundException() }
        return PayPointResponse.from(payPoint)
    }

    @Transactional
    fun deposit(request: PayPointRequest.DepositRequest) {

        try {
            // ... 결제 처리 로직 (외부 API 일것이니 transaction 제외 -> 트랜잭션 템플릿 사용할까?
            val payPoint = payPointRepository.findByIdForUpdate(request.payPointId).orElseThrow { EntityNotFoundException() } // ToDo locking 처리 후 멀티스레딩 테스트
            payPoint.addPoint(request.amount)
            eventPublisher.publishEvent(PayPointEvent.PayDepositProcessEvent(payPoint.id, request.tradeId, true))

        } catch (e: Exception) {
            eventPublisher.publishEvent(PayPointEvent.PayDepositProcessEvent(request.payPointId, request.tradeId,false))
        }
    }
}