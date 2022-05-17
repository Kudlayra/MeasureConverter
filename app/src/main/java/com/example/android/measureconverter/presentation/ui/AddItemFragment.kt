package com.example.android.measureconverter.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.measureconverter.app.UnitConverterApp
import com.example.android.measureconverter.R
import com.example.android.measureconverter.databinding.FragmentAddItemBinding
import com.example.android.measureconverter.presentation.ui.main.MainViewModel
import com.example.android.measureconverter.presentation.ui.main.MainViewModelFactory
import javax.inject.Inject

class AddItemFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }
    private var checkedRadioButton: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddItemBinding.inflate(inflater)
        (requireContext().applicationContext as UnitConverterApp).appComponent.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonToAdd.setOnClickListener {

            findNavController().navigate(R.id.action_addItemFragment2_to_mainFragment2)
        }
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_addItemFragment2_to_mainFragment2)
        }
        binding.radioButtonLength.setOnClickListener {
            checkedRadioButton = binding.radioButtonLength.text.toString()
            viewModel.changeType(checkedRadioButton)
        }
        binding.radioButtonWeight.setOnClickListener {
            checkedRadioButton = binding.radioButtonWeight.text.toString()
            viewModel.changeType(checkedRadioButton)
        }
        binding.radioButtonDegrees.setOnClickListener {
            checkedRadioButton = binding.radioButtonDegrees.text.toString()
            viewModel.changeType(checkedRadioButton)
        }
        viewModel.stringWithType.observe(this.viewLifecycleOwner){ it ->
            binding.textView3.text = it
        }
    }

    companion object {
        fun newInstance() = AddItemFragment
    }

    private fun getNewUnitData() {
        val type = checkedRadioButton
        val unitName = binding.inputName.text
        val shortName = binding.inputShortName.text
        val convertingData = binding.inputCalculateData.text
    }

}