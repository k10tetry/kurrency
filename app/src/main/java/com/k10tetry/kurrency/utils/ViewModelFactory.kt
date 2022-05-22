package com.k10tetry.kurrency.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.k10tetry.kurrency.di.scopes.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ViewModelFactory @Inject constructor(private val mapper: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator = mapper[modelClass]
        if (creator == null) {
            mapper.forEach {
                if (modelClass.isAssignableFrom(it.key)) {
                    creator = it.value
                    return@forEach
                }
            }
        }
        return try {
            creator?.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}