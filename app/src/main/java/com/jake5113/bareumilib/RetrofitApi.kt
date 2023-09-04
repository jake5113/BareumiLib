package com.jake5113.bareumilib

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitApi {
    @GET("data.json")
    fun getBooks(): Call<BooksResponse>

    @GET("v3/search/book")
    fun searchBookImg(
        @Header("Authorization") clientId: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("query") bookName: String,
        @Query("sort") sort: String,
        @Query("target") target: String
    ): Call<KakaoBookApi>
}


