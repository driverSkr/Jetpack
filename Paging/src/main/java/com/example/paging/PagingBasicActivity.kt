package com.example.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.adapter.RepoAdapter
import com.example.paging.viewModel.PagingBasicViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PagingBasicActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(PagingBasicViewModel::class.java) }

    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_basic)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoAdapter
        lifecycleScope.launch {
            /**
             * collect()函数有点类似于Rxjava中的subscribe()函数，总之就是订阅了之后，消息就会源源不断往这里传
             */
            viewModel.getPagingData().collect { pagingData ->
                /**
                 * 调用了RepoAdapter的submitData()函数。
                 * 这个函数是触发Paging 3分页功能的核心，调用这个函数之后，Paging 3就开始工作了
                 */
                repoAdapter.submitData(pagingData)
            }
        }
        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this,"Load Error: ${state.error.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}