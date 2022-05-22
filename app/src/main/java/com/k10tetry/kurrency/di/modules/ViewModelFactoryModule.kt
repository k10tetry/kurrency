package com.k10tetry.kurrency.di.modules

import androidx.lifecycle.ViewModelProvider
import com.k10tetry.kurrency.di.scopes.ActivityScope
import com.k10tetry.kurrency.utils.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @ActivityScope
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}