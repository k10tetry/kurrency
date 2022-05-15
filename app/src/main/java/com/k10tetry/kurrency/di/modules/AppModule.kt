package com.k10tetry.kurrency.di.modules

import android.content.Context
import com.k10tetry.kurrency.KurrencyApp
import com.k10tetry.kurrency.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: KurrencyApp) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideApplication(): KurrencyApp = application

}