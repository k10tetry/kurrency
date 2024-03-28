package com.k10tetry.kurrency.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.k10tetry.kurrency.R
import com.k10tetry.kurrency.databinding.ActivityMainBinding
import com.k10tetry.kurrency.di.components.AppComponent
import com.k10tetry.kurrency.ui.base.BaseActivity
import com.k10tetry.kurrency.utils.NetworkUtils
import com.k10tetry.kurrency.utils.ViewModelFactory
import com.k10tetry.kurrency.utils.capitalisation
import com.k10tetry.kurrency.utils.toPx
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    @Inject
    lateinit var mMainRecycleAdapter: MainRecycleAdapter

    @Inject
    lateinit var networkUtils: NetworkUtils

    override fun injectActivity(appComponent: AppComponent) {
        appComponent.mainActivityComponent().create(this).inject(this)
    }

    override fun getViewBindings(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): MainViewModel =
        ViewModelProvider(this, mViewModelFactory)[MainViewModel::class.java]

    override fun init() {
        setSupportActionBar(mViewBinding.toolbar)
        initAdapter()
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {

        mViewModel.isLoading.observe(this) {
            mViewBinding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        mViewModel.kurrencyData.observe(this) {
            mMainRecycleAdapter.itemList = it
            mMainRecycleAdapter.notifyDataSetChanged()
        }

        networkUtils.connectivity.observe(this) { connection ->
            if (!connection) {
                mViewBinding.textConnectionStatus.text = getString(R.string.no_connection)
                mViewBinding.linearLayoutConnection.apply {
                    visibility = View.VISIBLE
                    setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources, R.color.inActiveColor, null
                        )
                    )
                }
            } else {
                if (mMainRecycleAdapter.itemCount == 0) {
                    mViewModel.fetchCoins()
                }
                mViewBinding.textConnectionStatus.text = getString(R.string.online)
                mViewBinding.linearLayoutConnection.apply {
                    setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources, R.color.activeColor, null
                        )
                    )
                    animate().apply {
                        alpha(1f)
                        duration = 1000L
                        startDelay = 1000L
                        setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                visibility = View.GONE
                            }
                        })
                    }
                }
            }
        }

    }

    private fun initAdapter() {
        mViewBinding.recycleView.run {
            adapter = mMainRecycleAdapter
            layoutManager = mLayoutManager
            addItemDecoration(MainItemDecorator())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_switch -> switchTheme()
            R.id.action_filter -> showDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {

        val materialDialog =
            MaterialAlertDialogBuilder(this).setTitle("Filter Kurrency").setSingleChoiceItems(
                ArrayAdapter(this,
                    androidx.constraintlayout.widget.R.layout.select_dialog_singlechoice_material,
                    FilterKurrency.values().map { it.name.capitalisation() }),
                mViewModel.filterType.ordinal
            ) { dialogInterface, position ->
                mViewModel.filterKurrency(FilterKurrency.values()[position])
                dialogInterface.dismiss()
            }

        materialDialog.create().show()
    }

    private fun switchTheme() {
        val mode =
            if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    inner class MainItemDecorator : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            val common = 16.toPx(resources)
            val bottom = when (parent.getChildAdapterPosition(view)) {
                parent.adapter?.itemCount?.minus(1) -> common
                else -> 0
            }
            outRect.set(common, common, common, bottom)
        }
    }
}
