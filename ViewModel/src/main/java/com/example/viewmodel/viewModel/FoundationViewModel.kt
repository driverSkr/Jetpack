package com.example.viewmodel.viewModel

import androidx.lifecycle.ViewModel

/**
* ViewModel:帮助Activity 分担一部分工作，它是专门用于存放与界面相关的数据的
* */
//只要是界面上能看得到的数据，它的相关变量都应该存放在ViewModel 中，而不是Activity 中，这样可以在一定程度上减少Activity 中的逻辑
//ViewModel 的生命周期和Activity 不同，它可以保证在手机屏幕发生旋转的时候不会被重新创建，只有当Activity 退出的时候才会跟着Activity 一起销毁
class FoundationViewModel: ViewModel() {

    var counter = 0
}