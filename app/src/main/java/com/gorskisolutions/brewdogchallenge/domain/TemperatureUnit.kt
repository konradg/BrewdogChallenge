package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TemperatureUnit(val value: Float, val unit: String) {
    override fun toString(): String = "$value\u00b0 ${unit.toUpperCase(Locale.ROOT)[0]}"
}
