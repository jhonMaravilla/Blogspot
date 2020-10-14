package com.kotlin.blogspot.ui.auth

import androidx.lifecycle.ViewModel
import com.kotlin.blogspot.repository.auth.AuthRepository

class AuthViewModel
constructor(
    val authRepository: AuthRepository
) : ViewModel() {
}