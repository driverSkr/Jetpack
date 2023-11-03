package com.example.livedata.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.livedata.entity.User
import com.example.livedata.logic.Repository

/**
 * 如果ViewModel 中的某个LiveData 对象是调用另外的方法获取的，那么我们就可以借助switchMap()方法，
 * 将这个LiveData 对象转换成另外一个可观察的LiveData 对象
 */
class LiveDataSwitchMapViewModel: ViewModel() {

    //用来观察userId的数据变化，
    private val userIdLiveData = MutableLiveData<String>()

    /**用来对另一个可观察的LiveData 对象进行转换**/
    /**
     * switchMap()方法同样接收两个参数：
     * * 第一个参数传入我们新增的userIdLiveData，switchMap()方法会对它进行观察；
     * * 第二个参数是一个转换函数，注意，我们必须在这个转换函数中返回一个LiveData 对象，
     * * * 因为switchMap()方法的工作原理就是要将转换函数中返回的LiveData 对象转换成另一个可观察的LiveData 对象。
     */
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

    /**
     * 我们再来梳理一遍它的整体工作流程。首先，
    当外部调用MainViewModel 的getUser()方法来获取用户数据时，并不会发起任何请求或者
    函数调用，只会将传入的userId值设置到userIdLiveData当中。一旦userIdLiveData的
    数据发生变化，那么观察userIdLiveData的switchMap()方法就会执行，并且调用我们编
    写的转换函数。然后在转换函数中调用Repository.getUser()方法获取真正的用户数据。
    同时，switchMap()方法会将Repository.getUser()方法返回的LiveData 对象转换成一
    个可观察的LiveData 对象，对于Activity 而言，只要去观察这个LiveData 对象就可以了
     */
}