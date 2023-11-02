package com.example.paging.logic.entity

import com.google.gson.annotations.SerializedName

data class Repo (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val starCount: Int
)