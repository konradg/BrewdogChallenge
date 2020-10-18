package com.gorskisolutions.brewdogchallenge.ui

import android.content.res.Resources

private val density by lazy { Resources.getSystem().displayMetrics.density }

val Int.dp: Int
    get() = (this * density).toInt()
