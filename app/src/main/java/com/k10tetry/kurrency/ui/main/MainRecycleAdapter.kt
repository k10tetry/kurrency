package com.k10tetry.kurrency.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.k10tetry.kurrency.R
import com.k10tetry.kurrency.data.model.Kurrency
import com.k10tetry.kurrency.databinding.ItemMainBinding
import com.k10tetry.kurrency.ui.base.BaseRecycleAdapter
import com.k10tetry.kurrency.ui.base.BaseRecycleHolder
import javax.inject.Inject

class MainRecycleAdapter @Inject constructor() : BaseRecycleAdapter<Kurrency>() {
    override fun getViewHolder(parent: ViewGroup): BaseRecycleHolder<Kurrency> {
        return MainViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class MainViewHolder(private val binding: ItemMainBinding) :
        BaseRecycleHolder<Kurrency>(binding.root) {
        override fun bind(item: Kurrency) {
            binding.textViewSymbol.text = item.symbol
            binding.textViewName.text = item.name
            binding.textViewStatus.text = if (item.isActive) "Active" else "InActive"
            binding.chipNew.visibility = if (item.isNew) View.VISIBLE else View.GONE

            val color = if (item.isActive) R.color.activeColor else R.color.inActiveColor
            binding.textViewStatus.setTextColor(
                ResourcesCompat.getColor(
                    binding.textViewStatus.context.resources,
                    color,
                    null
                )
            )
        }
    }

}