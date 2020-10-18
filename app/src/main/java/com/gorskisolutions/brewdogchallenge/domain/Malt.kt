package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class Malt(val name: String, val amount: WeightAmount)
