package com.mp.trade.service

import com.mp.trade.event.TradeEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class PointService {

    @EventListener
    fun listenTrade(event: TradeEvent) {

    }
}