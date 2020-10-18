package com.gorskisolutions.brewdogchallenge.beer.details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorskisolutions.brewdogchallenge.AppSchedulers
import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.gorskisolutions.brewdogchallenge.ui.ScreenState
import io.reactivex.rxjava3.core.Flowable

class DetailsViewModel @ViewModelInject constructor(
    private val getBeerDetailsInteractor: GetBeerDetailsInteractor,
    private val appSchedulers: AppSchedulers
): ViewModel() {

    private val _beer = MutableLiveData<Beer?>()
    val beer: LiveData<Beer?> = _beer

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getBeerDetails(id: String) =
        getBeerDetailsInteractor.getBeerDetails(id)
            .materialize()
            .flatMap { when {
                it.isOnNext -> Flowable.just(ScreenState.Success(it.value))
                it.isOnError -> Flowable.just(ScreenState.Error)
                else -> Flowable.empty()
            } }
            .startWith(Flowable.just(ScreenState.Loading))
            .subscribeOn(appSchedulers.processing)
            .observeOn(appSchedulers.main)
            .subscribe(::handleEvent, ::handleError)

    private fun handleEvent(screenState: ScreenState) {
        when (screenState) {
            is ScreenState.Success<*> -> {
                _beer.value = screenState.content as Beer
                _error.value = false
                _loading.value = false
            }
            is ScreenState.Error -> {
                _beer.value = null
                _error.value = true
                _loading.value = false
            }
            is ScreenState.Loading -> {
                _beer.value = null
                _error.value = false
                _loading.value = true
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        // Should not happen at runtime
        throwable.printStackTrace()
    }
}