package com.example.android.measureconverter.presentation.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.measureconverter.MainActivity
import com.example.android.measureconverter.R
import com.example.android.measureconverter.app.UnitConverterApp
import com.example.android.measureconverter.databinding.FragmentMainBinding
import com.example.android.measureconverter.presentation.adapter.AdapterForTable
import com.example.android.measureconverter.presentation.adapter.AdapterForUnits
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        val adapterForUnitsOne = AdapterForUnits ({ it, position ->
            viewModel.currentUnit(it)
            viewModel.changeSelectedUnit(position)
            lifecycle.coroutineScope.launch {
                viewModel.getCalculatedResultList(it, inputDataFromEditText()).collect() {
                    adapterForUnitsTwo.submitList(it)
                }
            }
            if (inputDataFromEditText().toDouble() == 1.0) {
                viewModel.changeUnitOnUi(it.unitName)
            } else viewModel.changeUnitOnUi(it.pluralName)
        }, viewModel.selectedUnit.value ?: 0, viewModel.lastSelectedUnit.value ?: 0)
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
            floatingActionButtonRefresh.setOnClickListener {
                showRecreateDatabaseDialog()
            }
            floatingActionButtonDelete.setOnClickListener {
                showDeleteUnitDialog()
            }
            mainFragmentConstraintLayout.setOnClickListener {
                hideKeyboard(requireContext(), view)
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
    private fun inputDataFromEditText(): String {
        return if (binding.inputAmount.text.isNullOrEmpty()) "0.0"
        else binding.inputAmount.text.toString()
    }

    private fun showDeleteUnitDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.Deleting))
            .setMessage("${getString(R.string.Do_you_really_want_to_delete_unit)} \"${viewModel.rightChosenUnit.value.toString()}\"")
            .setNegativeButton(getString(R.string.No)) {_, _ ->
            }
            .setPositiveButton(getString(R.string.Yes)) {_, _ ->
                lifecycle.coroutineScope.launch{
                    viewModel.delete()
                }
            }
            .show()

    }

    private fun showRecreateDatabaseDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.all_your_data_will_be_lost))
            .setMessage(R.string.reacrete_database_dialog_title)
            .setNegativeButton(getString(R.string.No)) {_, _ ->
            }
            .setPositiveButton(getString(R.string.Yes)) {_, _ ->
                recreateDatabase(requireContext())
            }
            .show()
    }
    private fun hideKeyboard(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun recreateDatabase(context: Context) {
        context.deleteDatabase("db")
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        if (context is Activity) {
            (context as Activity).finish()
        }
        Runtime.getRuntime().exit(0)
        //todo this function must be in the Domain module
        //it delete the database and restart App to create database with default data
    }
}