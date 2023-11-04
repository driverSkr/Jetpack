package com.example.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.entity.User

@Dao
interface UserDao {

    /**
     * 表示会将参数中传入的User对象插入数据库中，插入完成后还会将自动生成的主键id值返回
     */
    @Insert
    fun insertUser(user: User): Long

    /**
     * 表示会将参数中传入的User对象更新到数据库当中
     */
    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): LiveData<List<User>>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    /**
     * 表示会将参数传入的User对象从数据库中删除
     */
    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int
}