package com.mp.trade.api

import com.mp.trade.dto.TradeRequest
import com.mp.trade.service.TradeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TradeRest(
    val tradeService: TradeService
) {
    @PostMapping("/trade")
    fun openTrade(request: TradeRequest) = tradeService.openTrade(request)
}