package com.example.viewmodel.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.R
import com.example.viewmodel.viewModel.FoundationViewModel
import kotlinx.android.synthetic.main.activity_foundation_view_model.*

/**
* ViewModel的基本用法
* */
//一定要通过ViewModelProvider 来获取ViewModel 的实例
class FoundationViewModelActivity : AppCompatActivity() {

    /**绝对不可以直接去创建ViewModel 的实例，而是一定要通过ViewModelProvider 来获取ViewModel 的实例**/
    lateinit var viewModel: FoundationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foundation_view_model)

        viewModel = ViewModelProvider(this).get(FoundationViewModel::class.java)

        plusOneBtn.setOnClickListener {
            viewModel.counter ++
            //点击后更新数据
            refreshCounter()
        }
        //为了在activity刚创建时，显示数字
        refreshCounter()
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }
}