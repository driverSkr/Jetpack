package com.example.viewmodel.viewModel

import androidx.lifecycle.ViewModel

/**
* 向ViewModel 传递参数
* */
class ParametersViewModel(countReserved: Int): ViewModel() {

    var counter = countReserved
}