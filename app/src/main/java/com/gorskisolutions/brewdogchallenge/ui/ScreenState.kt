package com.gorskisolutions.brewdogchallenge.ui

sealed class ScreenState {
    class Success<T>(val content: T): ScreenState()
    object Error : ScreenState()
    object Loading : ScreenState()
}
