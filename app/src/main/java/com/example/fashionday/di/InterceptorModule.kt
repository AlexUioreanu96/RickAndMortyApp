package com.example.fashionday.di

import com.example.fashionday.data.network.api.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderInterceptorQualifier

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {
    @Provides
    @Singleton
    @HeaderInterceptorQualifier
    fun provideHeaderInterceptor(): Interceptor {
        return HeaderInterceptor()
    }
}
