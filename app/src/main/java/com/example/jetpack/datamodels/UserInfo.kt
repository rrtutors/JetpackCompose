package com.example.jetpack.datamodels

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val id: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)

data class GetUsersResponse(
    val page: Int,
    val data: List<UserInfo>,
    val support: Support,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("per_page")
    val perPage: Int,
)

data class Support(
    val url: String,
    val text: String
)
