package com.example.paging.logic.entity

import com.google.gson.annotations.SerializedName

class RepoResponse (
    @SerializedName("items") val items: List<Repo> = emptyList()
)