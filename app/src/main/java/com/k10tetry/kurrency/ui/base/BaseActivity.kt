package com.k10tetry.kurrency.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.k10tetry.kurrency.KurrencyApp
import com.k10tetry.kurrency.di.components.AppComponent
import com.k10tetry.kurrency.di.components.MainActivityComponent

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected val mViewModel: VM by lazy { getViewModel() }

    lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        injectActivity((application as KurrencyApp).appComponent)
        super.onCreate(savedInstanceState)

        mViewBinding = getViewBindings()
        setContentView(mViewBinding.root)

        init()
    }

    abstract fun injectActivity(appComponent: AppComponent)

    abstract fun getViewBindings(): VB

    abstract fun getViewModel(): VM

    abstract fun init()
}