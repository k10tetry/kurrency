package com.k10tetry.kurrency.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k10tetry.kurrency.data.model.Kurrency

@Dao
interface KurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kurrency: List<Kurrency>)

    @Query("SELECT * FROM ${Kurrency.TABLE}")
    suspend fun getAll(): List<Kurrency>
}