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

}