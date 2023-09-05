package com.example.loginformularyexample.di.modules

import com.example.loginformularyexample.AppConfig
import com.example.loginformularyexample.data.api.APISecure
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
object ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(appConfig: AppConfig): Retrofit {
        val url = "https://ilogicwms.com/global-app-config/${appConfig.serviceKey}/api-config.txt"
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAPISecureService(retrofit: Retrofit): APISecure {
        return retrofit.create(APISecure::class.java)
    }
}

