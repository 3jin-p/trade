package com.mp.trade.api

import io.leangen.graphql.metadata.strategy.DefaultInclusionStrategy
import org.springframework.stereotype.Component
import java.lang.reflect.AnnotatedElement
import java.lang.reflect.AnnotatedType
import java.lang.reflect.Field


@Component
class CompanionClassFilteringInclusionStrategy() : DefaultInclusionStrategy() {
    override fun includeOperation(elements: List<AnnotatedElement>, declaringType: AnnotatedType): Boolean {
        return if (isCompanionClass(elements)) {
            false
        } else {
            super.includeOperation(elements, declaringType)
        }
    }

    private fun isCompanionClass(elements: List<AnnotatedElement>): Boolean =
        (elements.firstOrNull { it is Field } as Field?)?.type?.typeName?.contains("\$Companion") ?: false
}