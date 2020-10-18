package com.gorskisolutions.brewdogchallenge.beer.list

import com.gorskisolutions.brewdogchallenge.domain.Beer
import com.gorskisolutions.brewdogchallenge.beer.BeerRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetBeersInteractor @Inject constructor(
    private val beerRepository: BeerRepository
) {
    fun getBeers(): Flowable<List<Beer>> = beerRepository.getBeers()
}
