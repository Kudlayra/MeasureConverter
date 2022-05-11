package com.example.android.measureconverter.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.measureconverter.R
import com.example.android.measureconverter.data.LengthUnit


class UnitsAdapter(private val context: Context,
                   private val listOfUnits: List<LengthUnit>,
                   private val clickListener: (LengthUnit) -> Unit
    ): RecyclerView.Adapter<UnitsAdapter.UnitsAdapterViewHolder>() {

    var toast: Toast? = null // variable for toast message in order to cancel Toast
    var selectedPosition = 0 // variable for single item selection

    class UnitsAdapterViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textRecyclerView)
        val cardView = view.findViewById<CardView>(R.id.recyclerviewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitsAdapterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_for_recyclerview, parent, false)
        return UnitsAdapterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: UnitsAdapterViewHolder, position: Int) {
        val item = listOfUnits[position]
        holder.textView.text = item.shortName
        holder.cardView.isActivated = selectedPosition == position
        holder.itemView.setOnClickListener {
            selectedPosition = position
            toast?.cancel()
            toast = Toast.makeText(context, item.nameForRecyclerView, Toast.LENGTH_SHORT)
            toast?.show()
            clickListener(item)
            notifyItemRangeChanged(0, listOfUnits.size)
        }
    }



    override fun getItemCount(): Int {
        return listOfUnits.size
    }
}