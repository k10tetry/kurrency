package com.k10tetry.kurrency.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.k10tetry.kurrency.di.qualifiers.ActivityContext
import com.k10tetry.kurrency.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    @ActivityScope
    fun provideContext(): Context = activity

    @Provides
    @ActivityScope
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun provideLayoutManager(@ActivityContext context: Context): LinearLayoutManager =
        LinearLayoutManager(context)

}