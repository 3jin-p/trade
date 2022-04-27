package com.mp.trade.repo

import com.mp.trade.domain.Trade
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TradeRepository: JpaRepository<Trade, UUID> {
}