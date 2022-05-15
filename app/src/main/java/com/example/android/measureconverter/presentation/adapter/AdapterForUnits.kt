package com.example.android.measureconverter.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.measureconverter.data.source.local.Units
import com.example.android.measureconverter.databinding.ItemForRecyclerviewBinding

class AdapterForUnits(private val onItemClicked: (Units) -> Unit): ListAdapter<Units, AdapterForUnits.AdapterViewHolder>(DiffCallback) {
    class AdapterViewHolder(private val binding: ItemForRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(unit: Units) {
            binding.textRecyclerView.text = unit.shortUnitName
        }
        val cardView = binding.recyclerviewItem
    }

    private var selectedPosition = 0
    private var lastSelectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val viewHolder = AdapterViewHolder(ItemForRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return viewHolder
    }
    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
        val position = holder.adapterPosition
        holder.itemView.isActivated = selectedPosition == position
        holder.itemView.setOnClickListener {
            onItemClicked(getItem(position))
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(position)
            lastSelectedPosition = position
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Units>() {
            override fun areItemsTheSame(oldItem: Units, newItem: Units): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Units, newItem: Units): Boolean {
                return oldItem == newItem
            }
        }
    }
}