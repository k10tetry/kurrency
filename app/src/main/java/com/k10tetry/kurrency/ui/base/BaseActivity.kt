package com.k10tetry.kurrency.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.k10tetry.kurrency.KurrencyApp
import com.k10tetry.kurrency.di.components.MainActivityComponent

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
        (application as KurrencyApp).appComponent.mainActivityComponent().create(this)

    abstract fun injectActivity(mainActivityComponent: MainActivityComponent)

    abstract fun getViewBindings(): VB

    abstract fun getViewModel(): VM

    abstract fun init()
}