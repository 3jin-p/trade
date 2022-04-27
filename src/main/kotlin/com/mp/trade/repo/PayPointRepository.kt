package com.mp.trade.repo

import com.mp.trade.domain.PayPoint
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PayPointRepository: JpaRepository<PayPoint, UUID> {
}