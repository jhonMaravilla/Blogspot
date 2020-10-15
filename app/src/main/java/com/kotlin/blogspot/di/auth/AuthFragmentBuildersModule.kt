package com.kotlin.blogspot.di.auth

import com.kotlin.blogspot.ui.auth.ForgotPasswordFragment
import com.kotlin.blogspot.ui.auth.LauncherFragment
import com.kotlin.blogspot.ui.auth.LoginFragment
import com.kotlin.blogspot.ui.auth.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeLauncherFragment(): LauncherFragment

    @ContributesAndroidInjector()
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector()
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

}