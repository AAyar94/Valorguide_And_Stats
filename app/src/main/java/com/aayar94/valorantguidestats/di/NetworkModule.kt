package com.aayar94.valorantguidestats.di

import com.aayar94.valorantguidestats.data.ValorantGameContentApiService
import com.aayar94.valorantguidestats.util.Constants.BASE_URL
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
object NetworkModule {
    @Singleton
    @Provides
    @Named("general")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(

        ).also { it.level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    @Named("general")
    fun provideHttpClient(@Named("general") okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(okHttpLoggingInterceptor)
            .build()

    }

    @Singleton
    @Provides
    @Named("general")
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("general")
    fun provideRetrofitInstance(
        @Named("general") okHttpClient: OkHttpClient,
        @Named("general") gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(@Named("general") retrofit: Retrofit): ValorantGameContentApiService {
        return retrofit.create(ValorantGameContentApiService::class.java)
    }
}