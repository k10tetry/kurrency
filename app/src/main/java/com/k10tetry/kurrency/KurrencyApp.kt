package com.k10tetry.kurrency

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.k10tetry.kurrency.di.components.AppComponent
import com.k10tetry.kurrency.di.components.DaggerAppComponent

class KurrencyApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)

        val mode =
            if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

}