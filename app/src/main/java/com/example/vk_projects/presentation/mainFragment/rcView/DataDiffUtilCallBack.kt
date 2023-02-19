package com.example.vk_projects.presentation.mainFragment.rcView

import androidx.recyclerview.widget.DiffUtil
import com.example.vk_projects.data.remoteDataSousce.Item

class DataDiffCallback: DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}