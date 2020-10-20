package com.kotlin.blogspot.di

import com.kotlin.blogspot.di.auth.AuthFragmentBuildersModule
import com.kotlin.blogspot.di.auth.AuthModule
import com.kotlin.blogspot.di.auth.AuthScope
import com.kotlin.blogspot.di.auth.AuthViewModelModule
import com.kotlin.blogspot.ui.auth.AuthActivity
import com.kotlin.blogspot.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [
            AuthFragmentBuildersModule::class, AuthModule::class, AuthViewModelModule::class
        ]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(
        modules = [

        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}