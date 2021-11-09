package com.example.jetpack.datamodels

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface Api {
    @Headers("Accept: Application/json")
    @GET("users")
    suspend fun getUserList(@Query("page") page: Int): GetUsersResponse

    @Headers("Accept: Application/json")
    @GET("assets/test.json")
    fun fetchBookList(): Call<GetBoolListResponse>?
}

















