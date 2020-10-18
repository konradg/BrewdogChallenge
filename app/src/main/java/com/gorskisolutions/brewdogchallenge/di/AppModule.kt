package com.gorskisolutions.brewdogchallenge.di

import com.gorskisolutions.brewdogchallenge.util.AppSchedulers
import com.gorskisolutions.brewdogchallenge.util.AppSchedulersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    fun provideAppSchedulers(): AppSchedulers = AppSchedulersImpl
}
