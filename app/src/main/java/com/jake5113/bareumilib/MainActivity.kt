package com.jake5113.bareumilib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jake5113.bareumilib.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        getImgUrlFromNaver("9788911026319")
    }

    private fun getBooksFromFirebase() {
        val retrofit =
            RetrofitHelper.getRetrofitInstance("https://bareumilib-default-rtdb.asia-southeast1.firebasedatabase.app/")

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

                        if (booksItem.imgUrl.isNullOrEmpty() && !booksItem.iSBN.isNullOrEmpty()){
                            //booksItem.imgUrl = getImgUrlFromNaver(booksItem.iSBN!!)
                        }

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

    private fun getImgUrlFromNaver(isbn: String) {
        val CLIENT_ID = "5ZdEh87Zvmp6dRapnTrc"
        val CLIENT_SECRET = "cpEvsDKmfj"

        val retrofit =
            RetrofitHelper.getRetrofitInstance("https://openapi.naver.com/v1/search/")

        val retrofitApiService = retrofit.create(RetrofitApi::class.java)
        retrofitApiService.getImgUrl(CLIENT_ID, CLIENT_SECRET, isbn).enqueue(
            object: Callback<NaverBooksItem>{
                override fun onResponse(
                    call: Call<NaverBooksItem>,
                    response: Response<NaverBooksItem>
                ) {
                    // TODO: XML 파싱중
                    Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<NaverBooksItem>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

private fun getDefaultValueForEmptyField(value: String?, defaultValue: String): String =
    if (value.isNullOrEmpty()) defaultValue else value



