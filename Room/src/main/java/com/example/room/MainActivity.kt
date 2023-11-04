package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.activity.RoomBasicActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        room_basic.setOnClickListener {
            startActivity(Intent(this,RoomBasicActivity::class.java))
        }
    }
}