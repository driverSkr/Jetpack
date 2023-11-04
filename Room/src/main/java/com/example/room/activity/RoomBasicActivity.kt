package com.example.room.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.room.R
import com.example.room.database.AppDatabase
import com.example.room.entity.User
import com.example.room.factory.RoomViewModelFactory
import com.example.room.viewModel.RoomViewModel
import kotlinx.android.synthetic.main.activity_room_basic.*
import kotlin.concurrent.thread

/**Room数据库的简单实践**/
class RoomBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_basic)

        val viewModel = ViewModelProvider(this,RoomViewModelFactory(this)).get(RoomViewModel::class.java)
        /**监控数据库的数据变化**/
        viewModel.roomLiveData.observe(this) {
            val result = StringBuilder().run {
                for ( user in it) {
                    append("${user.firstName} ${user.lastName}'s age is ${user.age} years old \n")
                }
                toString()
            }
            //将格式化的数据显示出来
            user_show.text = result
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom","Brady",40)
        val user2 = User("Tom","Hanks",63)

        //添加数据
        addDataBtn.setOnClickListener {
            thread {
                /**
                 * 将insertUser()方法返回的主键id值赋值给原来的User对象
                 * 因为使用@Update和@Delete注解去更新和删除数据时都是基于这个id值来操作的
                 */
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        //修改数据
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        //删除数据
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
                userDao.deleteUserByLastName("Brady")
            }
        }
    }
}