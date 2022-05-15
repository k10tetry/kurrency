package com.k10tetry.kurrency.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleAdapter<T> : RecyclerView.Adapter<BaseRecycleHolder<T>>() {

    var itemList: List<T> = emptyList()

    abstract fun getViewHolder(parent: ViewGroup): BaseRecycleHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecycleHolder<T> =
        getViewHolder(parent)

    override
    fun onBindViewHolder(holder: BaseRecycleHolder<T>, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount(): Int = itemList.size
}