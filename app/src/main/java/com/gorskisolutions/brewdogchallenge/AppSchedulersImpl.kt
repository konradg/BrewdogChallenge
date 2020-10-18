package com.gorskisolutions.brewdogchallenge

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

interface AppSchedulers {
    val processing: Scheduler
    val main: Scheduler
}

object AppSchedulersImpl : AppSchedulers {
    override val processing: Scheduler = Schedulers.io()
    override val main: Scheduler = AndroidSchedulers.mainThread()
}
