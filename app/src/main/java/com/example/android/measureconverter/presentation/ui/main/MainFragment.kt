package com.example.android.measureconverter.presentation.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        const val LENGTH = "length"
        const val WEIGHT = "weight"
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
            viewModel.currentUnit(it)
            lifecycle.coroutineScope.launch {
                viewModel.getCalculatedResultList(it, inputDataFromEditText()).collect() {
                    adapterForUnitsTwo.submitList(it)
                }
            }
            if (inputDataFromEditText().toDouble() == 1.0) {
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
            inputAmount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        lifecycle.coroutineScope.launch {
                            viewModel.getCalculatedResultList(viewModel.currentUnit!!, inputDataFromEditText()).collect() {
                                adapterForUnitsTwo.submitList(it)
                            }
                        }
                        if (inputDataFromEditText().toDouble() == 1.0) {
                        viewModel.changeUnitOnUi(viewModel.currentUnit!!.unitName)
                        } else viewModel.changeUnitOnUi(viewModel.currentUnit!!.pluralName)
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
            changeToLengthRadioButton.setOnClickListener {
                viewModel.changeType(LENGTH)
                lifecycle.coroutineScope.launch {
                    viewModel.getList(viewModel.selectedType.value.toString()).collect() {
                        adapterForUnitsOne.submitList(it)
                    }
                }
            }
            changeToWeightRadioButton.setOnClickListener {
                viewModel.changeType(WEIGHT)
                lifecycle.coroutineScope.launch {
                    viewModel.getList(viewModel.selectedType.value.toString()).collect() {
                        adapterForUnitsOne.submitList(it)
                    }
                }
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.getList(viewModel.selectedType.value.toString()).collect() {
                adapterForUnitsOne.submitList(it)
            }
        }
        viewModel.rightChosenUnit.observe(this.viewLifecycleOwner) { it ->
            binding.textViewWithUnit.text = it
        }

    }
    fun inputDataFromEditText(): String {
        return if (binding.inputAmount.text.isNullOrEmpty()) "0.0"
        else binding.inputAmount.text.toString()
    }
}