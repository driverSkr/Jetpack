package com.example.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {
    /**
     * @PrimaryKey注解将它设为了主键
     * @param: autoGenerate参数指定成true，使得主键的值是自动生成的
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
