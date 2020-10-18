package com.gorskisolutions.brewdogchallenge.beer.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gorskisolutions.brewdogchallenge.AppSchedulers
import com.gorskisolutions.brewdogchallenge.beer.Beer
import org.reactivestreams.Publisher
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val getBeersInteractor: GetBeersInteractor,
    private val appSchedulers: AppSchedulers
) : ViewModel() {

    private val _screenState = LiveDataReactiveStreams.fromPublisher(getBeers())
    val screenState: LiveData<List<Beer>> = _screenState

    private fun getBeers(): Publisher<List<Beer>> =
        getBeersInteractor.getBeers()
            .subscribeOn(appSchedulers.processing)
            .observeOn(appSchedulers.main)
            .onErrorReturnItem(emptyList()) // TODO use different screen state
}
