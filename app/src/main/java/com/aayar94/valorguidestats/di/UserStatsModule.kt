package com.aayar94.valorguidestats.di

import com.aayar94.valorguidestats.data.ValorantUserStatsAPI
import com.aayar94.valorguidestats.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserStatsModule {

    @Singleton
    @Provides
    @Named("user_stats")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(

        ).also { it.level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    @Named("user_stats")
    fun provideHttpClient(@Named("user_stats") okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(okHttpLoggingInterceptor)
            .build()

    }

    @Singleton
    @Provides
    @Named("user_stats")
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("user_stats")
    fun provideRetrofitInstance(
        @Named("user_stats") okHttpClient: OkHttpClient,
        @Named("user_stats") gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.USER_STATS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(@Named("user_stats") retrofit: Retrofit): ValorantUserStatsAPI {
        return retrofit.create(ValorantUserStatsAPI::class.java)
    }

}