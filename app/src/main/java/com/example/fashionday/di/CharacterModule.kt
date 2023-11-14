package com.example.fashionday.di

import com.example.fashionday.data.repository.CharactersRepository
import com.example.fashionday.data.repository.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CharacterModule {
    @Singleton
    @Binds
    fun bindPlaceRepository(repository: CharactersRepositoryImpl): CharactersRepository

}