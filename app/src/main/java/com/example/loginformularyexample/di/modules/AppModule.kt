package com.example.loginformularyexample.di.modules

import android.content.Context
import com.example.loginformularyexample.AppConfig
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.InputStreamReader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppConfig(context: Context): AppConfig {
        val fileInputStream = context.openFileInput("app.config")
        val inputReader = InputStreamReader(fileInputStream)
        val output = inputReader.readText()
        val appConfig: JsonObject = Gson().fromJson(output, JsonObject::class.java)

        //Obtener el código de acceso y la etiqueta del servicio
        val serviceKey = appConfig.get("serviceKey").asString
        val serviceLabel = appConfig.get("serviceLabel").asString

        return AppConfig(serviceKey, serviceLabel)
    }
}