package com.kotlin.blogspot.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kotlin.blogspot.R
import kotlinx.android.synthetic.main.fragment_launcher.*

class LauncherFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login.setOnClickListener(this)
        register.setOnClickListener(this)
        forgot_password.setOnClickListener(this)

        focusable_view.requestFocus()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.login -> {
                findNavController().navigate(R.id.action_launcherFragment_to_loginFragment)
            }

            R.id.register -> {
                findNavController().navigate(R.id.action_launcherFragment_to_registerFragment)
            }

            R.id.forgot_password -> {
                findNavController().navigate(R.id.action_launcherFragment_to_forgotPasswordFragment)
            }
        }
    }
}