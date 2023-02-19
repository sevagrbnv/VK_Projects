package com.example.vk_projects.presentation.mainFragment.rcView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.vk_projects.R
import com.example.vk_projects.data.remoteDataSousce.Item
import com.example.vk_projects.databinding.ListItemBinding

class DataAdapter: ListAdapter<Item, DataViewHolder>(
    DataDiffCallback()
) {

    var onItemClickListener: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = R.layout.list_item
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val listItem = getItem(position)
        val binding = holder.binding

        when (binding) {
            is ListItemBinding  -> {
                Glide.with(holder.itemView.context).load(listItem.icon_url).into(binding.logo)
                binding.name.text = listItem.name
                binding.root.setOnClickListener {
                    onItemClickListener?.invoke(listItem)
                }
            }
        }
    }
}