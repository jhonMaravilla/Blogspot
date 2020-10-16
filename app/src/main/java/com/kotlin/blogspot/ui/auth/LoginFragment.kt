package com.kotlin.blogspot.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.kotlin.blogspot.R
import com.kotlin.blogspot.util.GenericApiResponse


class LoginFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.testLogin().observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is GenericApiResponse.ApiSuccessResponse -> {
                    Log.d(TAG, "onViewCreated: ${response.body}")
                }

                is GenericApiResponse.ApiErrorResponse -> {
                    Log.d(TAG, "onViewCreated: ${response.errorMessage}")
                }

                is GenericApiResponse.ApiEmptyResponse -> {
                    Log.d(TAG, "onViewCreated: EMPTY")
                }
            }
        })
    }
}