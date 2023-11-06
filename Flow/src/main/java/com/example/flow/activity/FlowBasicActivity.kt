package com.example.flow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flow.R
import com.example.flow.viewModel.FlowBasicViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Flow的基本用法
 */
/**实现一个计时器功能**/
class FlowBasicActivity : AppCompatActivity() {

    private val viewModel by viewModels<FlowBasicViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_basic)

        val textView = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            lifecycleScope.launch {
                /**调用collect函数就相当于把水龙头接到水管上并打开，这样从水源发送过来的任何数据，我们在水龙头这边都可以接收到**/
                viewModel.timeFlow.collectLatest { time ->
                    textView.text = time.toString()
                    delay(3000)
                }
            }
        }
    }
}