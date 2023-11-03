package com.example.livedata.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.livedata.R
import com.example.livedata.factory.LiveDataViewModelFactory
import com.example.livedata.viewModel.LiveDataViewModel
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved",0)
        viewModel = ViewModelProvider(this,LiveDataViewModelFactory(countReserved))
                    .get(LiveDataViewModel::class.java)

        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        /**任何LiveData对象都可以调用它的observe()方法来观察数据的变化**/
        /**
         * observe()方法接收两个参数：
         * 第一个参数是一个LifecycleOwner对象，有没有觉得很熟悉？没错，Activity 本身就是一个LifecycleOwner对象，因此直接传this就好；
         * 第二个参数是一个Observer接口，当counter中包含的数据发生变化时，就会回调到这里
         */
        viewModel.counter.observe(this) { count ->
            infoText.text = count.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}