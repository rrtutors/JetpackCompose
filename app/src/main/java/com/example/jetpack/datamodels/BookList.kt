package com.example.jetpack.datamodels

import com.google.gson.annotations.SerializedName

data class GetBoolListResponse (
    val booklist: List<Book>,
    val success: Long
)

data class Book (
    val id: String,
    val title: String,
    val author: String,
    val img: String,
    @SerializedName("is_paid")
    val isPaid: String,
    val price: String,
    @SerializedName("thumb_nail")
    val thumbNail: String,
    val source: String,
    @SerializedName("download_path")
    val downloadPath: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("catebory_name")
    val categoryName: String
)

data class CategoryWithBooks(val id:String,val name:String,val bookList:List<Book>)
