package com.proway.diexample.di

import com.proway.diexample.service.GithubServices
import com.proway.diexample.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun providesGithubServices(): GithubServices = RetrofitService.getGithubServices()
}