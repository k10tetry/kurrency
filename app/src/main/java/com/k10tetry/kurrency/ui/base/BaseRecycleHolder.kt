package com.k10tetry.kurrency.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}