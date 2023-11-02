package com.example.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R
import com.example.paging.logic.entity.Repo

/**
 * Paging 3是必须和RecyclerView结合使用,且必须继承自PagingDataAdapter
 * 注意：
 * ①相比于一个传统的RecyclerView Adapter，这里最特殊的地方就是要提供一个COMPARATOR
 *** 因为Paging 3在内部会使用DiffUtil来管理数据变化，所以这个COMPARATOR是必须的
 * ②我们并不需要传递数据源给到父类，因为数据源是由Paging 3在内部自己管理的
 * ③同时也不需要重写getItemCount()函数了，原因也是相同的，有多少条数据Paging 3自己就能够知道
 */
class RepoAdapter: PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.name.text = repo.name
            holder.description.text = repo.description
            holder.starCount.text = repo.starCount.toString()
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name_text)
        val description: TextView = itemView.findViewById(R.id.description_text)
        val starCount: TextView = itemView.findViewById(R.id.star_count_text)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }

        }
    }
}