package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class MashParams(val temp: TemperatureUnit, val duration: Int?)
