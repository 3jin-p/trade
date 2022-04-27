package com.mp.trade.api

import com.mp.trade.service.PayPointService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class PayPointRest(
    val payPointService: PayPointService
) {
    @GetMapping("/pay-point")
    fun getPayPoint(@RequestParam id: UUID) = payPointService.getPayPoint(id)
}