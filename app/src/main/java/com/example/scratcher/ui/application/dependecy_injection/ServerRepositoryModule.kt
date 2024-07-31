package com.example.scratcher.ui.application.dependecy_injection

import com.example.scratcher.ui.repositories.server.api.O2Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServerRepositoryModule {


    @AppUnauthorizedHttpClient
    @Provides
    @Singleton
    fun provideUnauthorizedOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        @AppUnauthorizedHttpClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideO2Api(
        @AppUnauthorizedHttpClient okHttpClient: OkHttpClient
    ) : O2Api {
        val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(O2Api::class.java)
    }
}