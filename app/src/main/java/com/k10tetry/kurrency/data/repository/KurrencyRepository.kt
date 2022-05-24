package com.k10tetry.kurrency.data.repository

import android.util.Log
import com.k10tetry.kurrency.data.local.db.KurrencyDao
import com.k10tetry.kurrency.data.model.Kurrency
import com.k10tetry.kurrency.data.remote.KurrencyApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KurrencyRepository @Inject constructor(
    private val apiService: KurrencyApiService,
    private val kurrencyDao: KurrencyDao
) {

    suspend fun getKurrency(): List<Kurrency> {
        var result = emptyList<Kurrency>()
        try {

            result = apiService.getAllCoins()

        } catch (e: Exception) {
            Log.e(TAG, "getKurrency: ${e.message}")
        }

        if (result.isEmpty()) {
            result = kurrencyDao.getAll()
        }

        return result
    }

    suspend fun saveKurrency(list: List<Kurrency>) {
        kurrencyDao.insert(list)
    }

    companion object {
        const val TAG = "KurrencyRepository"
    }

}