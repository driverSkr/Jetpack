package com.example.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewmodel.activity.FoundationViewModelActivity
import com.example.viewmodel.activity.ParametersViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foundation_viewModel.setOnClickListener {
            startActivity(Intent(this, FoundationViewModelActivity::class.java))
        }

        parameters_viewModel.setOnClickListener {
            startActivity(Intent(this, ParametersViewModelActivity::class.java))
        }
    }
}