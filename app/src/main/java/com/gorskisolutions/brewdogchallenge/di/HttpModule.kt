package com.gorskisolutions.brewdogchallenge.di

import android.content.Context
import com.gorskisolutions.brewdogchallenge.util.CacheInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

@Module
@InstallIn(ApplicationComponent::class)
object HttpModule {
    private const val BASE_URL = "https://api.punkapi.com/"
    private const val CACHE_TTL_SECONDS = 30
    private val CACHE_MAX_SIZE = 5.toMegabytes()
    private val JSON_MEDIA_TYPE = "application/json".toMediaType()

    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, CACHE_MAX_SIZE)

    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addNetworkInterceptor(CacheInterceptor(CACHE_TTL_SECONDS))
        .cache(cache)
        .build()

    @Provides
    fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    private fun Int.toMegabytes(): Long = this.toLong() * 1024L * 1024L
}
