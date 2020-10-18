package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class FermentationParams(val temp: TemperatureUnit)
