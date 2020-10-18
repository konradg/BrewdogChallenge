package com.gorskisolutions.brewdogchallenge.beer

import com.gorskisolutions.brewdogchallenge.domain.Beer
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerService {

    @GET("v2/beers")
    fun getBeers(): Single<List<Beer>>

    @GET("v2/beers/{id}")
    fun getBeer(@Query("id") id: String): Single<Beer>
}
