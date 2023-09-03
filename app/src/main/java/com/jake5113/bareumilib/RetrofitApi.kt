package com.jake5113.bareumilib

import RssFeed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitApi {
    @GET("data.json")
    fun getBooks(): Call<BooksResponse>

    @GET("/v1/search/book_adv.xml")
    fun searchBookByISBN(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("d_isbn") isbn: String
    ): Call<RssFeed>
}


