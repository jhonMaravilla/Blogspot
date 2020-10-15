package com.kotlin.blogspot.ui.auth

import androidx.lifecycle.ViewModel
import com.kotlin.blogspot.repository.auth.AuthRepository
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
) : ViewModel() {
}