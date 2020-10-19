package com.kotlin.blogspot.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.blogspot.api.auth.network_responses.LoginResponse
import com.kotlin.blogspot.api.auth.network_responses.RegistrationResponse
import com.kotlin.blogspot.model.AuthToken
import com.kotlin.blogspot.repository.auth.AuthRepository
import com.kotlin.blogspot.ui.BaseViewModel
import com.kotlin.blogspot.ui.DataState
import com.kotlin.blogspot.ui.auth.state.AuthStateEvent
import com.kotlin.blogspot.ui.auth.state.AuthStateEvent.*
import com.kotlin.blogspot.ui.auth.state.AuthViewState
import com.kotlin.blogspot.ui.auth.state.LoginFields
import com.kotlin.blogspot.ui.auth.state.RegistrationFields
import com.kotlin.blogspot.util.AbsentLiveData
import com.kotlin.blogspot.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
) : BaseViewModel<AuthStateEvent, AuthViewState>() {

    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
        when (stateEvent) {
            is LoginAttemptEvent -> {
                return AbsentLiveData.create()
            }

            is RegisterAttemptEvent -> {
                return AbsentLiveData.create()
            }

            is CheckPreviousAuthEvent -> {
                return AbsentLiveData.create()
            }
        }
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    fun setLoginFields(loginFields: LoginFields) {
        val update = getCurrentViewStateOrNew()
        if (update.loginFields == loginFields) {
            return
        }
        update.loginFields = loginFields
        _viewState.value = update
    }

    fun setRegistrationFields(registrationFields: RegistrationFields) {
        val update = getCurrentViewStateOrNew()
        if (update.registrationFields == registrationFields) {
            return
        }
        update.registrationFields = registrationFields
        _viewState.value = update
    }

    fun setAuthToken(authToken: AuthToken) {
        val update = getCurrentViewStateOrNew()
        if (update.authToken == authToken) {
            return
        }
        update.authToken = authToken
        _viewState.value = update
    }

}