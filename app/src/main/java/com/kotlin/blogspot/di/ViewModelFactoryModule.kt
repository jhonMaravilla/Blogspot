package com.kotlin.blogspot.di

import androidx.lifecycle.ViewModelProvider
import com.kotlin.blogspot.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    // This provides dependency for the factory in viewmodels package
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}