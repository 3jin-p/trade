package com.mp.trade

import com.mp.trade.config.RedisProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@EnableConfigurationProperties(value = [RedisProperties::class])
@SpringBootApplication
class TradeApplication

fun main(args: Array<String>) {
	runApplication<TradeApplication>(*args)
}
