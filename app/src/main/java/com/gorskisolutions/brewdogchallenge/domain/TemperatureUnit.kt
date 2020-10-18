package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class TemperatureUnit(val value: Float, val unit: String)
