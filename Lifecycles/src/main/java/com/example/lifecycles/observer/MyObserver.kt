package com.example.lifecycles.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Lifecycles 组件可以让任何一个类都能轻松感知到Activity 的生命周期，同时又不需要在Activity 中编写大量的逻辑处理
 */
class MyObserver(private val lifecycle: Lifecycle): LifecycleObserver {

    /**
     * 我们在方法上使用了@OnLifecycleEvent注解，并传入了一种生命周期事件。
     * 生命周期事件的类型一共有7种：ON_CREATE、ON_START、ON_RESUME、ON_PAUSE、ON_STOP和ON_DESTROY分别匹配Activity 中相应的生命周期回调；
     * 另外还有一种ON_ANY类型，表示可以匹配Activity 的任何生命周期回调
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun activityCreate() {

        /**可以在任何地方调用lifecycle.currentState来主动获知当前的生命周期状态
         * lifecycle.currentState返回的生命周期状态是一个枚举类型，
         *一共有INITIALIZED、DESTROYED、CREATED、STARTED、RESUMED这5种状态类型**/
        Log.d("MyObserver", lifecycle.currentState.toString())

        Log.d("MyObserver","activityCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d("MyObserver","activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun activityResume() {
        Log.d("MyObserver","activityResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityPause() {
        Log.d("MyObserver","activityPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d("MyObserver", "activityStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun activityDestroy() {
        Log.d("MyObserver","activityDestroy")
    }
}