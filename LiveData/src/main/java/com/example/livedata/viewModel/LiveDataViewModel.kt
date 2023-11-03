package com.example.livedata.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * LiveData的基本用法
 */
class LiveDataViewModel(countReserved: Int): ViewModel() {

    /**永远只暴露不可变的LiveData 给外部
     * 这样在非ViewModel 中就只能观察LiveData 的数据变化，而不能给LiveData 设置数据**/
    val counter: LiveData<Int> get() = _counter

    /**
     * MutableLiveData是一种可变的LiveData ，它的用法很简单，主要有3种读写数据的方法，分别是getValue()、setValue()和postValue()方法。
     * * getValue()方法用于获取LiveData 中包含的数据；
     * * setValue()方法用于给LiveData 设置数据，但是只能在主线程中调用；
     * * postValue()方法用于在非主线程中给LiveData 设置数据。
     */
    /**可变的LiveData 尽量不要暴露给外部**/
    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}