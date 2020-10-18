package com.gorskisolutions.brewdogchallenge.ui

import android.content.res.Resources
import android.view.View

private val density by lazy { Resources.getSystem().displayMetrics.density }

val Int.dp: Int
    get() = (this * density).toInt()

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
