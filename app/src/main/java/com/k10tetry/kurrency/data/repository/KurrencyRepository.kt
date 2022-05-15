package com.k10tetry.kurrency.data.repository

import android.util.Log
import com.k10tetry.kurrency.data.local.db.KurrencyDao
import com.k10tetry.kurrency.data.model.Kurrency
import com.k10tetry.kurrency.data.remote.KurrencyApiService
import com.k10tetry.kurrency.di.scopes.ActivityScope
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScope
class KurrencyRepository @Inject constructor(
    private val apiService: KurrencyApiService,
    private val kurrencyDao: KurrencyDao
) {

    suspend fun getKurrency(): List<Kurrency> {
        try {

            val result = apiService.getAllCoins()

            if (result.isNotEmpty()) {
                kurrencyDao.insert(result)
            }

        } catch (e: Exception) {
            Log.e(TAG, "getKurrency: ${e.message}")
        }

        return kurrencyDao.getAll()
    }

    companion object {
        const val TAG = "KurrencyRepository"
    }

}