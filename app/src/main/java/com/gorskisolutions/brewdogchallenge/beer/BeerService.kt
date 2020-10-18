package com.gorskisolutions.brewdogchallenge.beer

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BeerService {

    @GET("v2/beers")
    fun getBeers(): Single<List<Beer>>
}
