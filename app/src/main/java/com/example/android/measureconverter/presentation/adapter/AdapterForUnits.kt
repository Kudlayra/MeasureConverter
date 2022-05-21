package com.example.android.measureconverter.presentation.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.measureconverter.databinding.ItemForRecyclerviewBinding
import com.example.android.measureconverter.domain.models.UnitToAdd

class AdapterForUnits(private val onItemClicked: (UnitToAdd, Int) -> Unit, private var selectedPosition: Int, private var lastSelectedPosition: Int): ListAdapter<UnitToAdd, AdapterForUnits.AdapterViewHolder>(DiffCallback) {
    class AdapterViewHolder(private val binding: ItemForRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(unit: UnitToAdd) {
            binding.textRecyclerView.text = unit.shortUnitName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val viewHolder = AdapterViewHolder(ItemForRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return viewHolder
    }
    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
        val position = holder.adapterPosition
        Log.d("1234", "$lastSelectedPosition")
        holder.itemView.isActivated = selectedPosition == position
        if (holder.itemView.isActivated) onItemClicked(getItem(position), position)
        holder.itemView.setOnClickListener {
            onItemClicked(getItem(position), position)
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(position)
            lastSelectedPosition = position
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<UnitToAdd>() {
            override fun areItemsTheSame(oldItem: UnitToAdd, newItem: UnitToAdd): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UnitToAdd, newItem: UnitToAdd): Boolean {
                return oldItem == newItem
            }
        }
    }
}