package com.example.themovieapp.di

import com.example.core.domain.repository.MovieRepository
import com.example.core.domain.usecases.GetMoviesUseCase
import com.example.core.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCaseTest(remoteRepository: MovieRepository): UseCases {
        return UseCases(
                getMoviesUseCase = GetMoviesUseCase(remoteRepository)
        )
    }
}