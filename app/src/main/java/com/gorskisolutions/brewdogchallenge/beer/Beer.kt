package com.gorskisolutions.brewdogchallenge.beer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Beer(
    val id: String,
    @SerialName("image_url") val imageUrl: String,
    val name: String,
    val abv: Float
)
