package com.jake5113.bareumilib

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("data.json")
    fun getBooks(): Call<BooksResponse>
}