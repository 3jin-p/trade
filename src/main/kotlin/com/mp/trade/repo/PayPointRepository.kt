package com.mp.trade.repo

import com.mp.trade.domain.PayPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*
import javax.persistence.LockModeType

interface PayPointRepository: JpaRepository<PayPoint, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from PayPoint p WHERE p.id = :id")
    fun findByIdForUpdate(@Param(value = "id") id: UUID): Optional<PayPoint>
}