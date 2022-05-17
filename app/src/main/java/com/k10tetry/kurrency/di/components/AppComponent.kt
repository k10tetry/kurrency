package com.k10tetry.kurrency.di.components

import android.content.Context
import com.k10tetry.kurrency.di.modules.DatabaseModule
import com.k10tetry.kurrency.di.modules.NetworkModule
import com.k10tetry.kurrency.di.modules.AppSubComponent
import com.k10tetry.kurrency.di.qualifiers.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubComponent::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context): AppComponent
    }

    fun mainActivityComponent(): MainActivityComponent.Factory
}