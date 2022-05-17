package com.k10tetry.kurrency.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.k10tetry.kurrency.data.model.Kurrency

@Database(entities = [Kurrency::class], version = 1, exportSchema = false)
abstract class KurrencyDatabase : RoomDatabase() {
    abstract fun kurrencyDao(): KurrencyDao
}