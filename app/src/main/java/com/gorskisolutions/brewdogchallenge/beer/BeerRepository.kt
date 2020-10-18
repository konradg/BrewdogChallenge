package com.gorskisolutions.brewdogchallenge.beer

import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

interface BeerRepository {
    fun getBeers(): Flowable<List<Beer>>
    fun getBeer(id: String): Flowable<Beer>
}

class BeerRepositoryImpl @Inject constructor(
    private val beerService: BeerService
) : BeerRepository {
    private val cache: MutableMap<String, Beer> = mutableMapOf()

    override fun getBeers(): Flowable<List<Beer>> =
        beerService.getBeers()
            .toFlowable()
            .doOnNext(this::cacheItems)

    override fun getBeer(id: String): Flowable<Beer> = when {
        cache.contains(id) -> Flowable.fromSupplier { cache[id] }
        else -> beerService.getBeer(id)
            .toFlowable()
            .doOnNext(this::cacheItem)
    }

    private fun cacheItem(beer: Beer) = cache.put(beer.id, beer)

    private fun cacheItems(items: List<Beer>) {
        items
            .map { (it.id to it) }
            .also(cache::putAll)
    }
}
