package com.plcoding.jetpackcomposepokedex.data.di

import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.OpenAIService
import com.plcoding.jetpackcomposepokedex.data.datasource.remote.api.OpenAIServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object OpenAIServiceModule {

    @Singleton
    @Provides
    fun provideOpenAIService(): OpenAIService = OpenAIServiceImpl()
}
