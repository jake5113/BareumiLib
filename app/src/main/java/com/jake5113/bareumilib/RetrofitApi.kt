package com.jake5113.bareumilib

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("data.json")
    fun getBooks(): Call<BooksResponse>
}

