package com.example.livedata.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livedata.viewModel.LiveDataViewModel

class LiveDataViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LiveDataViewModel(countReserved) as T
    }
}