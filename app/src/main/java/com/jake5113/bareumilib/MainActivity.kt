package com.jake5113.bareumilib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jake5113.bareumilib.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var booksList: List<BooksItem> = listOf()
    val bookListFragment = BookListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragment 추가
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, bookListFragment)
                .commit()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://bareumilib-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitApiService = retrofit.create(BooksApi::class.java)
        retrofitApiService.getBooks().enqueue(
            object : Callback<BooksResponse> {
                override fun onResponse(
                    call: Call<BooksResponse>,
                    response: Response<BooksResponse>
                ) {
                    val booksResponse = response.body()
                    booksList = booksResponse!!.books
                    bookListFragment.setBooksList(booksList)
                }

                override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "실패", Toast.LENGTH_SHORT).show()
                }

            }
        )

    }
}
