package com.example.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.viewModel.ParametersViewModel

/**
* activity中借助ViewModelProvider.Factory将参数传递给ViewModel
* */
class ParametersViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ParametersViewModel(countReserved) as T
    }
}