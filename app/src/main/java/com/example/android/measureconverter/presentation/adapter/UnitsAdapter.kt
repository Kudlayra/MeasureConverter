package com.example.android.measureconverter.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.measureconverter.R
import com.example.android.measureconverter.data.LengthUnit
import com.example.android.measureconverter.presentation.ui.main.MainViewModel
import com.example.android.measureconverter.presentation.ui.main.MainViewModelFactory

class UnitsAdapter(private val context: Context, private val listOfUnits: List<LengthUnit>): RecyclerView.Adapter<UnitsAdapter.UnitsAdapterViewHolder>() {

    var toast: Toast? = null // variable for toast message in order to cancel Toast

    class UnitsAdapterViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textRecyclerView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitsAdapterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_for_recyclerview, parent, false)
        return UnitsAdapterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: UnitsAdapterViewHolder, position: Int) {
        val item = listOfUnits[position]
        holder.textView.text = item.shortName
        holder.itemView.setOnClickListener {
            toast?.cancel()
            toast = Toast.makeText(context, item.nameForRecyclerView, Toast.LENGTH_SHORT)
            toast?.view
            toast?.show()
        }
    }

    override fun getItemCount(): Int {
        return listOfUnits.size
    }
}