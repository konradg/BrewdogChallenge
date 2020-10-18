package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class Ingredients(
    val malt: List<Malt>,
    val hops: List<Hop>
)
