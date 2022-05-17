package com.k10tetry.kurrency.di.modules

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.k10tetry.kurrency.di.qualifiers.ActivityContext
import com.k10tetry.kurrency.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun provideLayoutManager(@ActivityContext context: Context): LinearLayoutManager =
        LinearLayoutManager(context)

}