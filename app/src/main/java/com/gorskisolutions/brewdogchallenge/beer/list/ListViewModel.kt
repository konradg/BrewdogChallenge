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

    private val _beers = LiveDataReactiveStreams.fromPublisher(getBeers())
    val beers: LiveData<List<Beer>> = _beers

    private fun getBeers(): Publisher<List<Beer>> =
        getBeersInteractor.getBeers()
            .subscribeOn(appSchedulers.processing)
            .observeOn(appSchedulers.main)
            .toFlowable()
}
