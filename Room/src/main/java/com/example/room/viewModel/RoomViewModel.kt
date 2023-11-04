package com.example.room.viewModel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.room.database.AppDatabase
import com.example.room.entity.User

@SuppressLint("StaticFieldLeak")
class RoomViewModel( context: Context): ViewModel() {

    private val userDao = AppDatabase.getDatabase(context).userDao()

    /**
     * 监控数据库的数据变化
     */
    val roomLiveData: LiveData<List<User>> = userDao.loadAllUsers()
}