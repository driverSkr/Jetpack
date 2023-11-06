package com.example.flow.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

/**
 * @Author: driverSkr
 * @Time: 2023/11/6 14:03
 * @Description: StateFlow$
 */
class StateFlowBasicViewModel: ViewModel() {

    private val _stateFlow = MutableStateFlow(0)

    val stateFlow = _stateFlow.asStateFlow()

    fun startTimer() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                _stateFlow.value += 1
            }
        },0,1000)
    }
}