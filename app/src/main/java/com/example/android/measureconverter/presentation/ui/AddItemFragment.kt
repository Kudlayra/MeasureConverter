package com.example.android.measureconverter.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.Navigation
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
        viewModel.checkedRadioButton(viewModel.checkedRadioButton.value!!)
        binding.buttonToAdd.setOnClickListener {
            checkIsEmptyInputField()
            checkInputCalculateData()
            if (checkIsEmptyInputField() && checkInputCalculateData()) {
                lifecycle.coroutineScope.launch {
                    getNewUnitData()
                }
                Navigation.findNavController(view).apply {
                    popBackStack()
                }
            }
        }
        binding.cancelButton.setOnClickListener {
            Navigation.findNavController(view).apply {
                popBackStack()
            }
        }
        binding.radioButtonLength.setOnClickListener {
            viewModel.checkedRadioButton(binding.radioButtonLength.text.toString().lowercase())
        }
        binding.radioButtonWeight.setOnClickListener {
            viewModel.checkedRadioButton(binding.radioButtonWeight.text.toString().lowercase())
        }
        viewModel.checkedRadioButton.observe(this.viewLifecycleOwner){
            binding.textView3.text = getString(R.string.How_many_unit_in_one_unit, changeTypeOnUi())
            binding.textView4.text = getString(R.string.Divide_your_unit_by_one, changeTypeOnUiTwo())
        }
        binding.addItemFragmentConstraintLayout.setOnClickListener {
            hideKeyboard(requireContext(), view)
        }
    }

    companion object {
        fun newInstance() = AddItemFragment
    }

    private suspend fun getNewUnitData() {
        val type = viewModel.checkedRadioButton.value
        val unitName = binding.inputName.text.toString()
        val shortName = binding.inputShortName.text.toString()
        val convertingData = binding.inputCalculateData.text.toString()
        viewModel.addNewItem(type!!, unitName, shortName, convertingData)
    }

    private fun checkIsEmptyInputField(): Boolean {
        binding.apply {
            if (inputName.text.isNullOrEmpty()) {
                textInputName.isErrorEnabled = true
                textInputName.error = getString(R.string.Enter_the_unit_name)
            } else {
                textInputName.isErrorEnabled = false
                textInputName.error = null
            }
            if (inputShortName.text.isNullOrEmpty()) {
                textInputShortName.isErrorEnabled = true
                textInputShortName.error = getString(R.string.Enter_the_unit_short_name)
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
    private fun checkInputCalculateData(): Boolean {
        if (!binding.inputCalculateData.text.isNullOrEmpty()) {
            if (binding.inputCalculateData.text.toString().toDouble() <= 0) {
                binding.textInputCalculateData.isErrorEnabled = true
                binding.textInputCalculateData.error = getString(R.string.Must_be_more_than_zero)
                return false
            } else {
                binding.textInputCalculateData.isErrorEnabled = false
                binding.textInputCalculateData.error = null
                return true
            }
        }
        return false
    }
    private fun hideKeyboard(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun changeTypeOnUi(): String {
        return when (viewModel.checkedRadioButton.value) {
            "length" -> "meters"
            "weight" -> "grams"
            else -> "meters"
        }
    }

    private fun changeTypeOnUiTwo(): String {
        return when (viewModel.checkedRadioButton.value) {
            "length" -> "meter"
            "weight" -> "gram"
            else -> "meter"
        }
    }
}