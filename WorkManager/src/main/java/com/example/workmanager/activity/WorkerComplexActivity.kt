package com.example.workmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.workmanager.databinding.ActivityWorkerComplexBinding
import com.example.workmanager.worker.SimpleWorker
import java.util.concurrent.TimeUnit

class WorkerComplexActivity : AppCompatActivity() {

    private var _binding: ActivityWorkerComplexBinding? = null

    private val binding: ActivityWorkerComplexBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWorkerComplexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var request: OneTimeWorkRequest

        binding.doWorkBtn.setOnClickListener {

            request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES) /**让SimpleWorker这个后台任务在5分钟后运行**/
                /**
                 * 给后台任务请求添加标签,可以通过标签来取消后台任务
                 * * WorkManager.getInstance(this).cancelAllWorkByTag("complex")
                 * 即使没有标签，也可以通过id来取消后台任务请求：
                 * * WorkManager.getInstance(this).cancelWorkById(request.id)
                 * 也可以使用如下代码来一次性取消所有后台任务请求:
                 * * WorkManager.getInstance(this).cancelAllWork()
                 */
                .addTag("complex")
                /**
                 *如果后台任务的doWork()方法中返回了Result.retry()，那么是可以结合setBackoffCriteria()方法来重新执行任务
                 * * 第一个参数则用于指定如果任务再次执行失败，下次重试的时间应该以什么样的形式延迟。分别是LINEAR和EXPONENTIAL，前者代表下次重试时间以线性的方式延迟，后者代表下次重试时间以指数的方式延迟
                 * * 第二个和第三个参数用于指定在多久之后重新执行任务，时间最短不能少于10 秒钟
                 */
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()
            WorkManager.getInstance(this).enqueue(request)

            /**
             * 使用如下代码对后台任务的运行结果进行监听
             */
            WorkManager.getInstance(this)
                /**可以调用getWorkInfosByTagLiveData()方法，监听同一标签名下所有后台任
                务请求的运行结果，用法是差不多的**/
                .getWorkInfoByIdLiveData(request.id)
                .observe(this) { workInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        Log.d("WorkerComplexActivity", "do work succeeded")
                    } else if (workInfo.state == WorkInfo.State.FAILED) {
                        Log.d("WorkerComplexActivity", "do work failed")
                    }
                }
        }
/**************************链式任务*************************************************/
        /**
         * 假设这里定义了3个独立的后台任务：同步数据、压缩数据和上传数据。
         * 现在我们想要实现先同步、再压缩、最后上传的功能，就可以借助链式任务来实现
         */
        /**
          val sync = ...
          val compress = ...
          val upload = ...
          WorkManager.getInstance(this)
              .beginWith(sync)
              .then(compress)
              .then(upload)
              .enqueue()
         */
    }
}