package com.k10tetry.kurrency.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.k10tetry.kurrency.KurrencyApp
import com.k10tetry.kurrency.di.components.ActivityComponent
import com.k10tetry.kurrency.di.components.DaggerActivityComponent
import com.k10tetry.kurrency.di.modules.ActivityModule

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected val mViewModel: VM by lazy { getViewModel() }

    lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        injectActivity(performInjection())
        super.onCreate(savedInstanceState)

        mViewBinding = getViewBindings()
        setContentView(mViewBinding.root)

        init()
    }

    private fun performInjection() =
        DaggerActivityComponent.builder()
            .appComponent((application as KurrencyApp).appComponent)
            .activityModule(ActivityModule(this))
            .build()

    abstract fun injectActivity(activityComponent: ActivityComponent)

    abstract fun getViewBindings(): VB

    abstract fun getViewModel(): VM

    abstract fun init()
}