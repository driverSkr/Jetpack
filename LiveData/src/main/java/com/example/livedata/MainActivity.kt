package com.example.livedata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.livedata.activity.LiveDataActivity
import com.example.livedata.activity.LiveDataMapActivity
import com.example.livedata.activity.LiveDataSwitchMapActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foundation_livedata.setOnClickListener {
            startActivity(Intent(this,LiveDataActivity::class.java))
        }

        livedata_map.setOnClickListener {
            startActivity(Intent(this,LiveDataMapActivity::class.java))
        }

        livedata_switchMap.setOnClickListener {
            startActivity(Intent(this,LiveDataSwitchMapActivity::class.java))
        }
    }
}