package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrewMethod(
    @SerialName("mash_temp") val mashingTemp: List<MashingParams>,
    val fermentation: FermentationParams,
    val twist: String?
)
