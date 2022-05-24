package com.k10tetry.kurrency.di.modules

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    fun provideLayoutManager(@ActivityContext context: Context): LinearLayoutManager =
        LinearLayoutManager(context)

}