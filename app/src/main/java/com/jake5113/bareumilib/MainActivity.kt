package com.jake5113.bareumilib

import RssFeed
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jake5113.bareumilib.databinding.ActivityMainBinding
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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

        getBooksFromFirebase()
        getImgUrlFromNaver()
    }

    private fun getBooksFromFirebase() {
        val retrofit =
            RetrofitHelper.getRetrofitInstanceFromFireBase("https://bareumilib-default-rtdb.asia-southeast1.firebasedatabase.app/")

        val retrofitApiService = retrofit.create(RetrofitApi::class.java)
        retrofitApiService.getBooks().enqueue(
            object : Callback<BooksResponse> {
                override fun onResponse(
                    call: Call<BooksResponse>,
                    response: Response<BooksResponse>
                ) {
                    val booksResponse = response.body()

                    // 키가 없는 값 초기화
                    booksResponse?.books?.map { booksItem ->
                        booksItem.page = getDefaultValueForEmptyField(booksItem.page, "페이지 정보 없음")
                        booksItem.symbol =
                            getDefaultValueForEmptyField(booksItem.symbol, "페이지 정보 없음")

                        booksItem.imgUrl = getDefaultValueForEmptyField(
                            booksItem.imgUrl,
                            "https://cdn.pixabay.com/photo/2020/03/27/17/03/shopping-4974313__340.jpg"
                        )
                    }
                    booksList = booksResponse!!.books
                    bookListFragment.setBooksList(booksList)
                }

                override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "실패", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun getImgUrlFromNaver(){
        val tikXml = TikXml.Builder().exceptionOnUnreadXml(false).build()

        // Retrofit 초기화 및 TikXml ConverterFactory 설정
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(TikXmlConverterFactory.create(tikXml))
            .build()

        val clientId = "5ZdEh87Zvmp6dRapnTrc"
        val clientSecret = "cpEvsDKmfj"

        val apiService = retrofit.create(RetrofitApi::class.java)
        val isbn = "9788943310721" // 검색하려는 ISBN 값

        apiService.searchBookByISBN(clientId, clientSecret, isbn).enqueue(
            object : Callback<RssFeed> {
                override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                    if (response.isSuccessful) {
                        val rssFeed = response.body()
                        if (rssFeed != null) {
                            val channel = rssFeed.channel
                            if (channel.items.isNotEmpty()) {
                                val firstItem = channel.items[0]
                                val author = firstItem.author
                                // 파싱된 데이터를 사용하거나 처리합니다.
                            } else {
                                // 아이템이 없을 때 처리
                                Toast.makeText(this@MainActivity, "아이템 X", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            // 응답이 null일 때 처리
                            Toast.makeText(this@MainActivity, "응답 널", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // 응답이 실패한 경우 처리
                        Toast.makeText(this@MainActivity, "응답 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                    // 네트워크 오류 처리
                }
            }
        )
    }
}


private fun getDefaultValueForEmptyField(value: String?, defaultValue: String): String =
    if (value.isNullOrEmpty()) defaultValue else value



