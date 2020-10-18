package com.gorskisolutions.brewdogchallenge.domain

import kotlinx.serialization.Serializable

@Serializable
data class WeightAmount(val value: Float, val unit: String)
