package com.example.imageexplorer.di

import com.example.imageexplorer.data.remote.ImageApiService
import com.example.imageexplorer.data.remote.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetroFit(client: OkHttpClient): Retrofit {
        //Имеется временные проблемы с доставанием с gradle
        return Retrofit.Builder().baseUrl("https://pixabay.com/api/?key=38091514-7cb315d0a956801ba964bf2d9")
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }
    @Provides
    fun provideClient(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().apply {
            writeTimeout(20, TimeUnit.SECONDS)
            readTimeout(20,TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ImageApiService {
        return retrofit.create(ImageApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: ImageApiService): ImageRepository {
        return ImageRepository(apiService)
    }

}