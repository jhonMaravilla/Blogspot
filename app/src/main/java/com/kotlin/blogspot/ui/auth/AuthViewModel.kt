package com.kotlin.blogspot.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.blogspot.api.auth.network_responses.LoginResponse
import com.kotlin.blogspot.api.auth.network_responses.RegistrationResponse
import com.kotlin.blogspot.repository.auth.AuthRepository
import com.kotlin.blogspot.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
) : ViewModel() {

    fun testLogin(): LiveData<GenericApiResponse<LoginResponse>> {
        return authRepository.testLoginRequest(
            "jhonjherick.maravilla@gmail.com",
            "P@ssw0rd"
        )
    }

    fun testRegister(): LiveData<GenericApiResponse<RegistrationResponse>> {
        return authRepository.testRegistrationRequest(
            "jakecyrus@gmail.com",
            "jakeCyrus",
            "P@ssw0rd",
            "P@ssw0rd"
        )
    }

}