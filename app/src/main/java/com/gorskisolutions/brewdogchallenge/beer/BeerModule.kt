package com.gorskisolutions.brewdogchallenge.beer

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
abstract class BeerModule {

    @Binds
    abstract fun bindBeerRepository(beerRepositoryImpl: BeerRepositoryImpl): BeerRepository

    companion object {
        @Provides
        fun provideBeerService(retrofit: Retrofit): BeerService =
            retrofit.create(BeerService::class.java)
    }
}
