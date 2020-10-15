package com.kotlin.blogspot.ui.auth

import android.os.Bundle
import com.kotlin.blogspot.R
import com.kotlin.blogspot.ui.BaseActivity


class AuthActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}