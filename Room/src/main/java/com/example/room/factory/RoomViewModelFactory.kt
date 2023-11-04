package com.example.room.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room.viewModel.RoomViewModel

class RoomViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RoomViewModel(context) as T
    }
}