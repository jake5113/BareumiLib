package com.jake5113.bareumilib

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitApi {
    @GET("data.json")
    fun getBooks(): Call<BooksResponse>

    @GET("book_adv.xml")
    fun getImgUrl(
        @Header("X-Naver-Client-Id") client_id: String,
        @Header("X-Naver-Client-Secret") client_secret: String,
        @Query("d_isbn") isbn: String
    ): Call<NaverBooksItem>
}


