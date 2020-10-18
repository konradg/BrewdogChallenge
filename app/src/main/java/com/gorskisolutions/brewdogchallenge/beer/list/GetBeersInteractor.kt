package com.gorskisolutions.brewdogchallenge.beer.list

import com.gorskisolutions.brewdogchallenge.beer.Beer
import com.gorskisolutions.brewdogchallenge.beer.BeerRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetBeersInteractor @Inject constructor(
    private val beerRepository: BeerRepository
) {
    fun getBeers(): Flowable<List<Beer>> = beerRepository.getBeers()
}