package com.example.android.measureconverter.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.android.measureconverter.app.UnitConverterApp
import com.example.android.measureconverter.R
import com.example.android.measureconverter.databinding.FragmentAddItemBinding
import com.example.android.measureconverter.presentation.ui.main.MainViewModel
import com.example.android.measureconverter.presentation.ui.main.MainViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddItemFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels {
        viewModelFactory
    }
    private var checkedRadioButton: String = "length"

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
            checkIsEmptyInputField()
            if (checkIsEmptyInputField()) {
                lifecycle.coroutineScope.launch {
                    getNewUnitData()
                }
                findNavController().navigate(R.id.action_addItemFragment2_to_mainFragment2)
            }
        }
        binding.cancelButton.setOnClickListener {
            Navigation.findNavController(view).apply {
                popBackStack()
            }
        }
        binding.radioButtonLength.setOnClickListener {
            checkedRadioButton = binding.radioButtonLength.text.toString().lowercase()
            viewModel.changeTypeOnUi(checkedRadioButton)
        }
        binding.radioButtonWeight.setOnClickListener {
            checkedRadioButton = binding.radioButtonWeight.text.toString().lowercase()
            viewModel.changeTypeOnUi(checkedRadioButton)
        }
        binding.radioButtonDegrees.setOnClickListener {
            checkedRadioButton = binding.radioButtonDegrees.text.toString().lowercase()
            viewModel.changeTypeOnUi(checkedRadioButton)
        }
        viewModel.stringWithType.observe(this.viewLifecycleOwner){ it ->
            binding.textView3.text = it
        }
    }

    companion object {
        fun newInstance() = AddItemFragment
    }

    private suspend fun getNewUnitData() {
        val type = checkedRadioButton
        val unitName = binding.inputName.text.toString()
        val shortName = binding.inputShortName.text.toString()
        val convertingData = binding.inputCalculateData.text.toString()
        viewModel.addNewItem(type, unitName, shortName, convertingData)
    }

    private fun checkIsEmptyInputField(): Boolean {
        binding.apply {
            if (inputName.text.isNullOrEmpty()) {
                textInputName.isErrorEnabled = true
                textInputName.error = getString(R.string.Enter_the_unit_name)
                return false
            } else {
                textInputName.isErrorEnabled = false
                textInputName.error = null
            }
            if (inputShortName.text.isNullOrEmpty()) {
                textInputShortName.isErrorEnabled = true
                textInputShortName.error = getString(R.string.Enter_the_unit_short_name)
                return false
            } else { textInputShortName.isErrorEnabled = false
                textInputShortName.error = null
            }
            if (inputCalculateData.text.isNullOrEmpty()) {
                textInputCalculateData.isErrorEnabled = true
                textInputCalculateData.error = getString(R.string.Enter_data_for_calculating)
                return false
            } else { textInputCalculateData.isErrorEnabled = false
            textInputCalculateData.error = null
            }
        }
        return true
    }

}