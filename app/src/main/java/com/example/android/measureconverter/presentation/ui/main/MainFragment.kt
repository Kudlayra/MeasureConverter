package com.example.android.measureconverter.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.measureconverter.data.ListOfUnits
import com.example.android.measureconverter.databinding.FragmentMainBinding
import com.example.android.measureconverter.presentation.adapter.UnitsAdapter

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterOne = UnitsAdapter(
            context = requireContext(),
            listOfUnits = viewModel.list,
            clickListener = { it -> viewModel.changeLeftUnit(it.nameForRecyclerView)
        } )
        val adapterTwo = UnitsAdapter(
            context = requireContext(),
            listOfUnits = viewModel.list,
            clickListener = { it -> viewModel.changeRightUnit(it.nameForRecyclerView)
        } )
        with(binding) {
            recyclerViewLeft.adapter = adapterOne
            recyclerViewLeft.layoutManager = LinearLayoutManager(this@MainFragment.context)
            recyclerViewRight.adapter = adapterTwo
            recyclerViewRight.layoutManager = LinearLayoutManager(this@MainFragment.context)
        }
        viewModel.leftChosenUnit.observe(this.viewLifecycleOwner) {
                it -> binding.textViewWithUnitOne.text = it }

        viewModel.rightChosenUnit.observe(this.viewLifecycleOwner) {
                item -> binding.textViewWithUnitTwo.text = item }
        }


}