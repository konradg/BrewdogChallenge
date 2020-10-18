package com.gorskisolutions.brewdogchallenge

import com.gorskisolutions.brewdogchallenge.util.AppSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

object TestAppSchedulers : AppSchedulers {
    override val processing: Scheduler = Schedulers.trampoline()
    override val main: Scheduler = Schedulers.trampoline()
}
