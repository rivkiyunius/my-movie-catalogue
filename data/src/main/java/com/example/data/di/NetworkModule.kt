package com.example.data.di

import android.content.Context
import com.example.data.source.remote.network.ApiService
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import com.example.data.utils.connectivitynetwork.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return interceptor
    }

    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val chainRequest = chain.request()
            val request = chainRequest.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNTVhY2Q4MGQ3MjI2NmUzMjkwMDg2ZmYzZTI4NjU5MiIsInN1YiI6IjVhYjQ1ZjRjYzNhMzY4NjE2MzAxNjM1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.I0mFW1ae1EnomFVf37gtBOUCRaeDPhvKCRftOWkDaVE"
                )
                .build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkhttp(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver =
        NetworkConnectivityObserver(context)
}