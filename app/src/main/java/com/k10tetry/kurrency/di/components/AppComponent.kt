package com.k10tetry.kurrency.di.components

import android.content.Context
import com.k10tetry.kurrency.di.modules.*
import com.k10tetry.kurrency.di.qualifiers.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubComponent::class, NetworkModule::class, DatabaseModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context): AppComponent
    }

    fun mainActivityComponent(): MainActivityComponent.Factory
}