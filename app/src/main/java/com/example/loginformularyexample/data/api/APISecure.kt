package com.example.loginformularyexample.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface APISecure {
    @FormUrlEncoded
    @POST("v3/secure/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("deviceType") deviceType: String,
        @Field("deviceUid") deviceUid: String,
        @Field("address") address: String,
        @Field("reference") reference: String
    ): Response<JsonObject>

    @POST("v3/secure/logout/")
    suspend fun logout(
        @Header("organizationId") organizationId: Int,
        @Header("securityToken") securityToken: String,
        @Header("userName") userName: String
    ): Response<JsonObject>
}