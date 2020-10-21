package com.kotlin.blogspot.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.kotlin.blogspot.R
import com.kotlin.blogspot.model.AuthToken
import com.kotlin.blogspot.ui.BaseActivity
import com.kotlin.blogspot.ui.ResponseType
import com.kotlin.blogspot.ui.ResponseType.*
import com.kotlin.blogspot.ui.main.MainActivity
import com.kotlin.blogspot.viewmodels.ViewModelProviderFactory
import javax.inject.Inject


class AuthActivity : BaseActivity(), NavController.OnDestinationChangedListener {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        findNavController(R.id.auth_nav_host_fragment).addOnDestinationChangedListener(this)

        subscribeObserver()
    }

    fun subscribeObserver() {

        // ViewState or Data
        viewModel.viewState.observe(this, Observer { viewState ->
            viewState.authToken?.let { authToken ->
                sessionManager.login(authToken)
            }
        })

        // StateEvent or Event Trigerring class
        viewModel.dataState.observe(this, Observer { dataState ->

            dataState.data?.let { data ->

                data.data?.let { event ->

                    event.getContentIfNotHandled()?.let { viewState ->
                        viewState.authToken?.let { authToken ->
                            viewModel.setAuthToken(authToken)
                        }
                    }

                }

                data.response?.let { event ->
                    event.getContentIfNotHandled()?.let { response ->

                        when (response.responseType) {
                            is Dialog -> {

                            }

                            is Toast -> {

                            }

                            is None -> {

                            }
                        }


                    }
                }

            }
        })

        sessionManager.cachedToken.observe(this, Observer { authToken ->
            Log.d(TAG, "MainActivity: subscribeObservers: AuthToken: ${authToken}")

            if (authToken != null && authToken.account_pk != -1 && authToken.token != null) {
                navigateToMainActivity()
            }

        })
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        viewModel.cancelActiveJobs()
    }
}