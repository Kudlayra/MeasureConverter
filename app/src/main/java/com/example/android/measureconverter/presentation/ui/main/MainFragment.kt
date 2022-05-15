package com.example.android.measureconverter.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.UnitConverterApp
import com.example.android.measureconverter.R
import com.example.android.measureconverter.databinding.FragmentMainBinding
import com.example.android.measureconverter.presentation.adapter.AdapterForUnits
import kotlinx.coroutines.launch

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(
            (activity?.application as UnitConverterApp).database.unitsDao()
        )
    }

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterForUnitsOne = AdapterForUnits { it -> viewModel.changeLeftUnit(it.pluralName) }
        val adapterForUnitsTwo = AdapterForUnits { it -> viewModel.changeRightUnit(it.pluralName) }
        with(binding) {
            recyclerViewLeft.adapter = adapterForUnitsOne
            recyclerViewLeft.layoutManager = LinearLayoutManager(this@MainFragment.context)
            recyclerViewRight.adapter = adapterForUnitsTwo
            recyclerViewRight.layoutManager = LinearLayoutManager(this@MainFragment.context)
            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment2_to_addItemFragment2)
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.fullList().collect() {
                adapterForUnitsOne.submitList(it)
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.fullList().collect() {
                adapterForUnitsTwo.submitList(it)
            }
        }
        viewModel.leftChosenUnit.observe(this.viewLifecycleOwner) {
                it -> binding.textViewWithUnitOne.text = it }

        viewModel.rightChosenUnit.observe(this.viewLifecycleOwner) {
                item -> binding.textViewWithUnitTwo.text = item }
        }

}