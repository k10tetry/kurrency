package com.k10tetry.kurrency.di.components

import android.content.Context
import com.k10tetry.kurrency.di.modules.MainActivityModule
import com.k10tetry.kurrency.di.modules.ViewModelFactoryModule
import com.k10tetry.kurrency.di.modules.ViewModelModule
import com.k10tetry.kurrency.di.qualifiers.ActivityContext
import com.k10tetry.kurrency.di.scopes.ActivityScope
import com.k10tetry.kurrency.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance @ActivityContext context: Context): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}