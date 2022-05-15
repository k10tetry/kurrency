package com.k10tetry.kurrency.di.components

import com.k10tetry.kurrency.di.modules.ActivityModule
import com.k10tetry.kurrency.di.scopes.ActivityScope
import com.k10tetry.kurrency.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}