package com.mp.trade.api

import com.mp.trade.service.PayPointService
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component
import java.util.*

@GraphQLApi
@Component
class PayPointQuery(
    val payPointService: PayPointService
) {
    @GraphQLQuery
    fun getPayPoint(id: UUID) = payPointService.getPayPoint(id)

}