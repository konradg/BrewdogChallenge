package com.gorskisolutions.brewdogchallenge.beer

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface BeerRepository {
    fun getBeers(): Single<List<Beer>>
}

class BeerRepositoryImpl @Inject constructor(
    private val beerService: BeerService
): BeerRepository {
    override fun getBeers(): Single<List<Beer>> =
        beerService.getBeers()
}
