package com.example.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R

/**
 * 作为RecyclerView的底部适配器，注意它必须继承自LoadStateAdapter
 */
class FooterAdapter(val retry: () -> Unit): LoadStateAdapter<FooterAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        val retryButton: TextView = itemView.findViewById(R.id.retry_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false)
        val holder = ViewHolder(view)
        holder.retryButton.setOnClickListener {
            retry()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState is LoadState.Error
    }
}