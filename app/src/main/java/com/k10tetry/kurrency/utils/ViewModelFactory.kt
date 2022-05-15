package com.k10tetry.kurrency.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.k10tetry.kurrency.data.repository.KurrencyRepository
import com.k10tetry.kurrency.di.scopes.ActivityScope
import com.k10tetry.kurrency.ui.main.MainViewModel
import javax.inject.Inject

@ActivityScope
class ViewModelFactory @Inject constructor(private val kurrencyRepository: KurrencyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(kurrencyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel ${modelClass.name}")
    }
}