package com.kotlin.blogspot.repository.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.kotlin.blogspot.api.auth.OpenApiAuthService
import com.kotlin.blogspot.api.auth.network_responses.LoginResponse
import com.kotlin.blogspot.api.auth.network_responses.RegistrationResponse
import com.kotlin.blogspot.model.AuthToken
import com.kotlin.blogspot.persistence.AccountPropertiesDao
import com.kotlin.blogspot.persistence.AuthTokenDao
import com.kotlin.blogspot.session.SessionManager
import com.kotlin.blogspot.ui.DataState
import com.kotlin.blogspot.ui.Response
import com.kotlin.blogspot.ui.ResponseType
import com.kotlin.blogspot.ui.auth.state.AuthViewState
import com.kotlin.blogspot.util.ErrorHandling.Companion.ERROR_UNKNOWN
import com.kotlin.blogspot.util.GenericApiResponse
import com.kotlin.blogspot.util.GenericApiResponse.*
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
) {

    /*
    *  If you will check, this will be triggered in our handleStateEvent in our ViewModel that's why their return value is the same
    *
    *  The .switchMap is used to convert the API return into a new DataType
    *
    *  We use when here becase our response is wrapped in a DataState class. So we distinguished the result of our request and return a correct data in our viewmodel
    *
    *
    */
    fun attemptLogin(email: String, password: String): LiveData<DataState<AuthViewState>> {
        return openApiAuthService.login(email, password)
            .switchMap { response ->
                object : LiveData<DataState<AuthViewState>>() {
                    override fun onActive() {
                        super.onActive()

                        when (response) {

                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    AuthViewState(
                                        authToken = AuthToken(
                                            response.body.pk,
                                            response.body.token
                                        )
                                    )
                                )
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }


    fun attemptRegistration(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataState<AuthViewState>> {
        return openApiAuthService.register(email, username, password, confirmPassword)
            .switchMap { response ->
                object : LiveData<DataState<AuthViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when (response) {
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    AuthViewState(
                                        authToken = AuthToken(response.body.pk, response.body.token)
                                    ),
                                    response = null
                                )
                            }
                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }


}