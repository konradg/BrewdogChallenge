package com.gorskisolutions.brewdogchallenge.beer.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gorskisolutions.brewdogchallenge.AppSchedulers
import com.gorskisolutions.brewdogchallenge.ui.ScreenState
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.reactivestreams.Publisher

class ListViewModel @ViewModelInject constructor(
    private val getBeersInteractor: GetBeersInteractor,
    private val appSchedulers: AppSchedulers
) : ViewModel() {

    private val _screenState = LiveDataReactiveStreams.fromPublisher(getBeers())
    val screenState: LiveData<ScreenState> = _screenState

    private fun getBeers(): Publisher<ScreenState> =
        getBeersInteractor.getBeers()
            .materialize()
            .flatMap {
                when  {
                    it.isOnNext -> Flowable.just(ScreenState.Success(it.value))
                    it.isOnError -> Flowable.just(ScreenState.Error)
                    else -> Flowable.empty()
                }
            }
            .subscribeOn(appSchedulers.processing)
            .observeOn(appSchedulers.main)
            .startWith(Single.just(ScreenState.Loading))
}
