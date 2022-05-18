package com.example.android.measureconverter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.measureconverter.databinding.TableItemBinding
import com.example.android.measureconverter.domain.models.UnitToAdd


class AdapterForTable(): ListAdapter<UnitToAdd, AdapterForTable.AdapterForTableViewHolder>(
    DiffCallback
) {
    class AdapterForTableViewHolder(private val binding: TableItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(unit: UnitToAdd) {
            binding.tableText.text = unit.pluralName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterForTableViewHolder {
        val viewHolder = AdapterForTableViewHolder(TableItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterForTableViewHolder, position: Int) {
        holder.bind(getItem(position))

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