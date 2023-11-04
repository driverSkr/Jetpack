package com.example.workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workmanager.activity.WorkerBasicActivity
import com.example.workmanager.activity.WorkerComplexActivity
import com.example.workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.workManagerBasic.setOnClickListener {
            startActivity(Intent(this,WorkerBasicActivity::class.java))
        }

        binding.workManagerComplex.setOnClickListener {
            startActivity(Intent(this,WorkerComplexActivity::class.java))
        }
    }
}