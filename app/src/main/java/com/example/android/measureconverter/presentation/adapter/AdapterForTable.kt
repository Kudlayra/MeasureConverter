package com.example.android.measureconverter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.measureconverter.databinding.TableItemBinding
import com.example.android.measureconverter.domain.models.CalculatedResult
import com.example.android.measureconverter.domain.models.UnitToAdd


class AdapterForTable(function: () -> Unit) : ListAdapter<CalculatedResult, AdapterForTable.AdapterForTableViewHolder>(
    DiffCallback
) {
    class AdapterForTableViewHolder(private val binding: TableItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(unit: CalculatedResult) {
            binding.tableText.text = "${unit.result} ${unit.unitName}"
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
        private val DiffCallback = object : DiffUtil.ItemCallback<CalculatedResult>() {
            override fun areItemsTheSame(oldItem: CalculatedResult, newItem: CalculatedResult): Boolean {
                return oldItem.unitName == newItem.unitName
            }

            override fun areContentsTheSame(oldItem: CalculatedResult, newItem: CalculatedResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}