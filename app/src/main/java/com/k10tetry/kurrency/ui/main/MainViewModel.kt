package com.k10tetry.kurrency.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k10tetry.kurrency.data.model.Kurrency
import com.k10tetry.kurrency.data.repository.KurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val kurrencyRepository: KurrencyRepository) :
    ViewModel() {

    private val _kurrencyData: MutableLiveData<List<Kurrency>> = MutableLiveData(emptyList())
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _localData: List<Kurrency> = emptyList()

    var filterType: FilterKurrency = FilterKurrency.NONE
        private set
    val kurrencyData: LiveData<List<Kurrency>> = _kurrencyData
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchCoins() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = kurrencyRepository.getKurrency()
                if (result.isNotEmpty()) {
                    saveKurrency(result)
                }
                _isLoading.postValue(false)
                _kurrencyData.postValue(result)
                _localData = result
            } catch (e: Exception) {
                _isLoading.postValue(false)
            }
        }
    }

    private fun saveKurrency(result: List<Kurrency>) {
        viewModelScope.launch(Dispatchers.Default) {
            kurrencyRepository.saveKurrency(result)
        }
    }

    fun filterKurrency(filterKurrency: FilterKurrency) {
        viewModelScope.launch {
            _kurrencyData.postValue(when (filterKurrency) {
                FilterKurrency.ACTIVE -> _localData.filter { it.isActive }
                FilterKurrency.NEW -> _localData.filter { it.isNew }
                FilterKurrency.INACTIVE -> _localData.filter { !it.isActive }
                FilterKurrency.NONE -> _localData
            })
            filterType = filterKurrency
        }
    }

}

enum class FilterKurrency {
    NEW, ACTIVE, INACTIVE, NONE
}