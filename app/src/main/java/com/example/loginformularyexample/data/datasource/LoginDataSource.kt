package com.example.loginformularyexample.data.datasource

import com.example.loginformularyexample.data.api.APISecure
import com.example.loginformularyexample.di.modules.ApiModule
import com.example.loginformularyexample.util.Result
import com.example.loginformularyexample.domain.models.User
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor (
    private val api : APISecure
) {

    suspend fun login(username: String, password: String) : Result<User> {
        try {
            return withContext(Dispatchers.IO) {
                val response = api.login(
                    username = username,
                    password = password,
                    deviceType = "Android",
                    deviceUid = "Unknown",
                    address = "Unknown",
                    reference = "",
                )
                if (response.isSuccessful){
                    val user = response.body()
                    if (user != null){
                        return@withContext Result.Success(user)
                    }
                }
                throw Exception("Error")
            }
        } catch (e: Throwable) {
            return Result.Error(e)
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}