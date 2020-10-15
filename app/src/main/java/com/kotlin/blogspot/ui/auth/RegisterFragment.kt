package com.kotlin.blogspot.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kotlin.blogspot.R
import com.kotlin.blogspot.util.GenericApiResponse

class RegisterFragment : BaseAuthFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.testRegister().observe(viewLifecycleOwner, Observer { response ->
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