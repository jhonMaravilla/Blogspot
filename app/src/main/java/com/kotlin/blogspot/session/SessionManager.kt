package com.kotlin.blogspot.session

import android.app.Application
import com.kotlin.blogspot.persistence.AuthTokenDao

class SessionManager
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
) {
}