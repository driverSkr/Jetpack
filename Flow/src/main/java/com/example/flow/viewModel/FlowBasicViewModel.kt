package com.example.flow.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * @Author: driverSkr
 * @Time: 2023/11/6 13:54
 * @Description: 为FlowBasicActivity提供数据源
 */
class FlowBasicViewModel:ViewModel() {

    val timeFlow = flow {
        var time = 0
        while (true) {
            /**可以理解为一个数据发送器，它会把传入的参数发送到水管当中**/
            emit(time)
            //delay函数是一个协程当中的挂起函数，只有在协程作用域或其他挂起函数中才能调用。
            // 因此可以看出，flow构建函数还会提供一个挂起函数的上下文给到函数体内部。
            delay(1000)
            time++
        }
    }
}