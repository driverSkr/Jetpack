package com.example.paging.logic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging.logic.entity.Repo
import com.example.paging.logic.service.GitHubService
import com.example.paging.source.RepoPagingSource
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 50

    private val gitHubService = GitHubService.create()

    /**
     * 这个函数的返回值是Flow<PagingData<Repo>>，注意除了Repo部分是可以改的，其他部分都是固定的
     */
    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE), //每页所包含的数据量
            pagingSourceFactory = { RepoPagingSource(gitHubService) } //Paging 3就会用它来作为用于分页的数据源了
        ).flow //调用.flow将它转换成一个Flow对象
    }
}