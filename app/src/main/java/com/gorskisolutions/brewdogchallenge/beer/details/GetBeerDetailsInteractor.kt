package com.gorskisolutions.brewdogchallenge.beer.details

import com.gorskisolutions.brewdogchallenge.beer.BeerRepository
import javax.inject.Inject

class GetBeerDetailsInteractor @Inject constructor(
    private val beerRepository: BeerRepository
) {
    fun getBeerDetails(id: String) = beerRepository.getBeer(id)
}
