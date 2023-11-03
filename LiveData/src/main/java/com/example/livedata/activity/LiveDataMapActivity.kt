package com.example.livedata.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.livedata.R
import com.example.livedata.viewModel.LiveDataMapViewModel
import kotlinx.android.synthetic.main.activity_live_data_map.*

class LiveDataMapActivity : AppCompatActivity() {

    lateinit var viewModel: LiveDataMapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_map)

        viewModel = ViewModelProvider(this).get(LiveDataMapViewModel::class.java)

        get_user.setOnClickListener {
            viewModel.getUser()
        }

        viewModel.userName.observe(this) {
            user_name.text = it
        }
    }
}