package com.k10tetry.kurrency.data.remote

import com.k10tetry.kurrency.data.model.Kurrency
import retrofit2.http.GET

interface KurrencyApiService {
    @GET(ApiConstants.COINS)
    suspend fun getAllCoins(): List<Kurrency>
}
