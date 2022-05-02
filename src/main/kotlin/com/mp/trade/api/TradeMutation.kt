package com.mp.trade.api

import com.mp.trade.dto.TradeRequest
import com.mp.trade.service.TradeService
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component

@GraphQLApi
@Component
class TradeMutation(
    val tradeService: TradeService
) {
    @GraphQLMutation
    fun openTrade(@GraphQLArgument(name = "request") request: TradeRequest) = tradeService.openTrade(request)
}