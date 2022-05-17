package com.k10tetry.kurrency

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.k10tetry.kurrency.di.components.AppComponent
import com.k10tetry.kurrency.di.components.DaggerAppComponent
import com.k10tetry.kurrency.utils.isNight

class KurrencyApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)

        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

}