package com.example.payskytask.appFeature.domain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.domain.UTILS.OnItemClickListener
import com.example.payskytask.databinding.RecycletItemBinding

class PostsAdapter(val onItemClickListener: OnItemClickListener) :
    ListAdapter<DataModelItem, PostsAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecycletItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class ItemViewHolder(private val binding: RecycletItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataModelItem) {
            binding.item = item
            binding.root.setOnClickListener { onItemClickListener.onClick(item) }
            binding.executePendingBindings()
        }
    }

}

class ItemDiffCallback : DiffUtil.ItemCallback<DataModelItem>() {
    override fun areItemsTheSame(oldItem: DataModelItem, newItem: DataModelItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataModelItem, newItem: DataModelItem): Boolean {
        return oldItem == newItem
    }
}