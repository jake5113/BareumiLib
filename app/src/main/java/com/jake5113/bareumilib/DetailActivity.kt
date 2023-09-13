package com.jake5113.bareumilib

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.jake5113.bareumilib.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedBook = intent.getParcelableExtra("BookData", BooksItem::class.java)
        Glide.with(this).load(receivedBook!!.imgUrl).into(binding.bookImg)
        binding.bookTitle.text = receivedBook.bookName
    }
}