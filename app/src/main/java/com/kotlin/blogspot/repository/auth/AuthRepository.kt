package com.kotlin.blogspot.repository.auth

import com.kotlin.blogspot.api.auth.OpenApiAuthService
import com.kotlin.blogspot.persistence.AccountPropertiesDao
import com.kotlin.blogspot.persistence.AuthTokenDao
import com.kotlin.blogspot.session.SessionManager
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
) {
}