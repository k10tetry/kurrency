package com.k10tetry.kurrency.di.modules

import android.content.Context
import androidx.room.Room
import com.k10tetry.kurrency.BuildConfig
import com.k10tetry.kurrency.data.local.db.KurrencyDao
import com.k10tetry.kurrency.data.local.db.KurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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