package com.mp.trade.domain

import javax.persistence.Embeddable

@Embeddable
data class Point(
    val point: Long
) {

    fun add(point: Point): Point {
        return Point(this.point + point.point);
    }

    fun minus(point: Point): Point {
        return Point(this.point - point.point);
    }

    fun moreThan(point: Point): Boolean {
        return this.point > point.point
    }

    fun orMoreThan(point: Point): Boolean {
        return this.point >= point.point
    }

    fun lessThan(point: Point): Boolean {
        return this.point < point.point
    }

    fun orLessThan(point: Point): Boolean {
        return this.point <= point.point
    }
}