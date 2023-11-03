package com.example.livedata.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.livedata.entity.User

/**
 * map()方法: 作用是将实际包含数据的LiveData 和仅用于观察数据的LiveData 进行转换
 *          将不需要的字段隐藏
 */
//LiveData的标准写法
class LiveDataMapViewModel: ViewModel() {

    /**声明成了private，以保证数据的封装性**/
    private val userLiveData = MutableLiveData<User>()

    /**
     * map()方法接收两个参数：
     * * 第一个参数是原始的LiveData 对象；
     * * 第二个参数是一个转换函数，我们在转换函数里编写具体的转换逻辑即可
     */
    //当userLiveData的数据发生变化时，map()方法会监听到变化并执行转换函数中的逻辑，
    // 然后再将转换之后的数据通知给userName的观察者
    val userName: LiveData<String> = Transformations.map(userLiveData) {
        "${it.firstName} ${it.lastName}"
    }

    fun getUser() {
        userLiveData.value = User("邹","聪波",23)
    }
}