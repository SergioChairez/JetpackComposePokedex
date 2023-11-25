package com.plcoding.jetpackcomposepokedex.data.di

import com.plcoding.jetpackcomposepokedex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class DefaultRetrofitApi

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @DefaultRetrofitApi
    @Singleton
    @Provides
    fun provideDefaultRetrofitApi(
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .build()
}

@Module
@InstallIn(SingletonComponent::class)
object ConverterFactoryModule {

    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()
}