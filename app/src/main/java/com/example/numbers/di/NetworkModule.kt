package com.example.numbers.di

import com.example.numbers.data.remote.BASE_URL
import com.example.numbers.data.remote.NumberApiService
import com.example.numbers.data.remote.NumberFactConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNumberApiService(
        retrofit: Retrofit
    ): NumberApiService {
        return retrofit.create(NumberApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        customConverterFactory: NumberFactConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(customConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}