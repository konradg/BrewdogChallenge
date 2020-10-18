package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class Hop(
    val name: String,
    val amount: WeightAmount,
    val add: String,
    val attribute: String
)
