package com.example.viewmodel.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.R
import com.example.viewmodel.factory.ParametersViewModelFactory
import com.example.viewmodel.viewModel.ParametersViewModel
import kotlinx.android.synthetic.main.activity_parameters_view_model.*

/**
* 向ViewModel传递参数：借助ViewModelProvider.Factory就可以实现了
* */
//在屏幕旋转的时候不会丢失数据
//借助SharedPreferences，即使在退出程序后又重新打开的情况下，数据仍然不会丢失
class ParametersViewModelActivity : AppCompatActivity() {

    lateinit var viewModel: ParametersViewModel
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameters_view_model)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved",0)
        viewModel = ViewModelProvider(this,ParametersViewModelFactory(countReserved))
                    .get(ParametersViewModel::class.java)

        plusOneBtn.setOnClickListener {
            viewModel.counter ++
            //点击后更新数据
            refreshCounter()
        }
        clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
        refreshCounter()
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }
}