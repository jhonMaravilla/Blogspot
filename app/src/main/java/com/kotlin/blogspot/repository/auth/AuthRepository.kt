package com.kotlin.blogspot.repository.auth

import androidx.lifecycle.LiveData
import com.kotlin.blogspot.api.auth.OpenApiAuthService
import com.kotlin.blogspot.api.auth.network_responses.LoginResponse
import com.kotlin.blogspot.api.auth.network_responses.RegistrationResponse
import com.kotlin.blogspot.persistence.AccountPropertiesDao
import com.kotlin.blogspot.persistence.AuthTokenDao
import com.kotlin.blogspot.session.SessionManager
import com.kotlin.blogspot.util.GenericApiResponse
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
) {

    fun testLoginRequest(email: String, password: String): LiveData<GenericApiResponse<LoginResponse>> {
        return openApiAuthService.login(email, password)
    }

    fun testRegistrationRequest(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>{
        return openApiAuthService.register(email, username, password, confirmPassword)
    }
}