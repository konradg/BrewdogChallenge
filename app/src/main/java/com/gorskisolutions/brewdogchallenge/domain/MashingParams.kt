package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class MashingParams(val temp: TemperatureUnit, val duration: Int?)
