package com.example.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * WorkManager 的基本用法
 * * ① 定义一个后台任务，并实现具体的任务逻辑
 */
/*
* 后台任务的写法非常固定，也很好理解。首先每一个后台任务都必须继承自Worker类，并调用它唯一的构造函数。
* 然后重写父类中的doWork()方法，在这个方法中编写具体的后台任务逻辑即可。
* */
class SimpleWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    /**
     * doWork()方法不会运行在主线程当中，因此你可以放心地在这里执行耗时逻辑
     */
    /*getBackgroundExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Result result = doWork();
                } catch (Throwable throwable) {}
            }
        });*/
    override fun doWork(): Result {
        Log.d("SimpleWorker", "do work in SimpleWorker")
        //doWork()方法要求返回一个Result对象，用于表示任务的运行结果，
        // 成功就返回Result.success()，失败就返回Result.failure()
        return Result.success()
    }
}