package com.example.loginformularyexample.domain.models

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    val userId: Int,
    val userName: String,
    val token: String,
    val userGroups:List<String>?,
    val userPrivileges:List<String>?
)