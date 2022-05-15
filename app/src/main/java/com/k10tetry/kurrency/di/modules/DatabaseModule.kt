package com.k10tetry.kurrency.di.modules

import android.content.Context
import androidx.room.Room
import com.k10tetry.kurrency.BuildConfig
import com.k10tetry.kurrency.data.local.db.KurrencyDao
import com.k10tetry.kurrency.data.local.db.KurrencyDatabase
import com.k10tetry.kurrency.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): KurrencyDatabase =
        Room.databaseBuilder(context, KurrencyDatabase::class.java, BuildConfig.DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideKurrencyDao(kurrencyDatabase: KurrencyDatabase): KurrencyDao =
        kurrencyDatabase.kurrencyDao()

}