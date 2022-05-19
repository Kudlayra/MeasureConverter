package com.example.android.measureconverter.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.measureconverter.app.UnitConverterApp
import com.example.android.measureconverter.R
import com.example.android.measureconverter.databinding.FragmentMainBinding
import com.example.android.measureconverter.presentation.adapter.AdapterForTable
import com.example.android.measureconverter.presentation.adapter.AdapterForUnits
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }
    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        (requireContext().applicationContext as UnitConverterApp).appComponent.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterForUnitsTwo = AdapterForTable { }
        val adapterForUnitsOne = AdapterForUnits { it ->
            lifecycle.coroutineScope.launch {
                viewModel.getCalculatedResultList(it, binding.inputAmount.text.toString()).collect() {
                    adapterForUnitsTwo.submitList(it)
                }
            }
            if (binding.inputAmount.text.toString().toDouble() == 1.0) {
                viewModel.changeUnitOnUi(it.unitName)
            } else viewModel.changeUnitOnUi(it.pluralName)
        }
        with(binding) {
            recyclerViewLeft.adapter = adapterForUnitsOne
            recyclerViewLeft.layoutManager = LinearLayoutManager(this@MainFragment.context)
            recyclerviewRight.adapter = adapterForUnitsTwo
            recyclerviewRight.layoutManager = LinearLayoutManager(this@MainFragment.context)
            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment2_to_addItemFragment2)
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.getList().collect() {
                adapterForUnitsOne.submitList(it)
            }
        }

        viewModel.rightChosenUnit.observe(this.viewLifecycleOwner) { it ->
            binding.textViewWithUnit.text = it
        }

    }
}