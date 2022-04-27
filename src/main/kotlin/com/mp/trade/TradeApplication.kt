package com.mp.trade

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class TradeApplication

fun main(args: Array<String>) {
	runApplication<TradeApplication>(*args)
}
