package com.example.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifecycles.observer.MyObserver

/**要通知MyObserver观察activity的生命周期，需借助LifecycleOwner
 * lifecycleOwner.lifecycle.addObserver(MyObserver())**/
/**
 * 只要你的Activity 是继承自AppCompatActivity的，或者你的Fragment 是继承自androidx.fragment.app.Fragment的，
 * 那么它们本身就是一个LifecycleOwner 的实例
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //承自AppCompatActivity的Activity本身就是一个LifecycleOwner 的实例
        lifecycle.addObserver(MyObserver(lifecycle))
    }
}