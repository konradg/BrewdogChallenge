package com.gorskisolutions.brewdogchallenge.ui

sealed class ScreenState {
    data class Success<T>(val content: T): ScreenState()
    object Error : ScreenState()
    object Loading : ScreenState()
}
