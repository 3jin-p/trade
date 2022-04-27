package com.mp.trade.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles

fun log(): Logger {
    return LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
}