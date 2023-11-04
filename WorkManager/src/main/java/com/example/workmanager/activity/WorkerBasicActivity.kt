package com.example.workmanager.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.workmanager.databinding.ActivityWorkerBasicBinding
import com.example.workmanager.worker.SimpleWorker

class WorkerBasicActivity : AppCompatActivity() {

    private var _binding: ActivityWorkerBasicBinding? = null

    private val binding:ActivityWorkerBasicBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWorkerBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doWorkBtn.setOnClickListener {
            /**
             * WorkManager 的基本用法：
             * ② 配置该后台任务的运行条件和约束信息，并构建后台任务请求；
             */
            /**OneTimeWorkRequest.Builder是WorkRequest.Builder的子类，用于构建单次运行的后台任务请求。**/
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            /**
             * WorkManager 的基本用法：
             * ③ 将该后台任务请求传入WorkManager 的enqueue()方法中，系统会在合适的时间运行
             */
            WorkManager.getInstance(this).enqueue(request)
        }
    }
}