package com.example.livedata.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.livedata.R
import com.example.livedata.viewModel.LiveDataSwitchMapViewModel
import kotlinx.android.synthetic.main.activity_live_data_switch_map.*

class LiveDataSwitchMapActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataSwitchMapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_switch_map)

        viewModel = ViewModelProvider(this).get(LiveDataSwitchMapViewModel::class.java)

        get_user.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this) {
            user_name.text = it.firstName
        }
    }
}