package com.gorskisolutions.brewdogchallenge.di

import com.bumptech.glide.module.AppGlideModule
import com.gorskisolutions.brewdogchallenge.AppSchedulers
import com.gorskisolutions.brewdogchallenge.AppSchedulersImpl
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
