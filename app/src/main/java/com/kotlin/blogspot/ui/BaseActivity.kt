package com.kotlin.blogspot.ui

import android.util.Log
import com.kotlin.blogspot.session.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), DataStateChangeListener {

    val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onDataStateChange(dataState: DataState<*>?) {

        dataState?.let { dataState ->
            GlobalScope.launch(Main) {

                displayProgressBar(dataState.loading.isLoading)

                dataState.error?.let { errorEvent ->
                    handleStateError(errorEvent)
                }

                dataState.data?.let { data ->
                    data.response?.let { responseEvent ->
                        handleStateResponse(responseEvent)
                    }
                }
            }
        }
    }

    abstract fun displayProgressBar(bool: Boolean)

    private fun handleStateError(event: Event<StateError>) {
        Log.d(TAG, "handleStateError: ${event.hasBeenHandled}")

        event.getContentIfNotHandled()?.let { stateError ->
            Log.d(TAG, "handleStateError: ${event.hasBeenHandled}")

            when (stateError.response.responseType) {
                is ResponseType.Toast -> {
                    stateError.response.message?.let { message ->
                        displayToast(message)
                    }
                }

                is ResponseType.Dialog -> {
                    stateError.response.message?.let { message ->
                        displayErrorDialog(message)
                    }
                }

                is ResponseType.None -> {
                    Log.i(TAG, "handleStateError: ${stateError.response.message}")
                }
            }
        }
    }

    private fun handleStateResponse(event: Event<Response>) {
        event.getContentIfNotHandled()?.let { response ->

            when (response.responseType) {
                is ResponseType.Toast -> {
                    response.message?.let { message ->
                        displayToast(message)
                    }
                }

                is ResponseType.Dialog -> {
                    response.message?.let { message ->
                        displaySuccessDialog(message)
                    }
                }

                is ResponseType.None -> {
                    Log.i(TAG, "handleStateResponse: ${response.message}")
                }
            }

        }
    }


}