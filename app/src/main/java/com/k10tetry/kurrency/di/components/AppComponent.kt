package com.k10tetry.kurrency.di.components

import com.k10tetry.kurrency.KurrencyApp
import com.k10tetry.kurrency.data.local.db.KurrencyDao
import com.k10tetry.kurrency.data.remote.KurrencyApiService
import com.k10tetry.kurrency.di.modules.AppModule
import com.k10tetry.kurrency.di.modules.DatabaseModule
import com.k10tetry.kurrency.di.modules.NetworkModule
import com.k10tetry.kurrency.utils.NetworkUtils
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(kurrencyApp: KurrencyApp)

    fun getKurrencyApiService(): KurrencyApiService

    fun getNetworkUtils(): NetworkUtils

    fun getKurrencyDao(): KurrencyDao
}