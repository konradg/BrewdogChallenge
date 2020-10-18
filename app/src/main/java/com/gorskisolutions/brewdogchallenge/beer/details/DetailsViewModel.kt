package com.gorskisolutions.brewdogchallenge.beer.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorskisolutions.brewdogchallenge.util.AppSchedulers
import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.gorskisolutions.brewdogchallenge.ui.ScreenState
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable

class DetailsViewModel @ViewModelInject constructor(
    private val getBeerDetailsInteractor: GetBeerDetailsInteractor,
    private val appSchedulers: AppSchedulers
): ViewModel() {

    private var disposable: Disposable? = null

    private val _beer = MutableLiveData<Beer>()
    val beer: LiveData<Beer> = _beer

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        disposable = null
    }

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
            .run { disposable = this }

    private fun handleEvent(screenState: ScreenState) {
        when (screenState) {
            is ScreenState.Success<*> -> {
                _beer.value = screenState.content as Beer
            }
            is ScreenState.Error -> {
                _error.value = true
            }
            is ScreenState.Loading -> {
                _loading.value = true
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        // Stream processing error, should not happen at runtime
        throwable.printStackTrace()
    }
}
