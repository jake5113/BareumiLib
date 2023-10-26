package com.jake5113.bareumilib

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.jake5113.bareumilib.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedBook = intent.getParcelableExtra("BookData", BooksItem::class.java)
        Glide.with(this).load(receivedBook!!.imgUrl).into(binding.bookImg)
        binding.tvTitle.text = "책 제목 : " + receivedBook.bookName
        binding.tvAuthor.text = "작가 : " + receivedBook.author
        binding.tvPulisher.text = "출판사 : " + receivedBook.publisher
        binding.tvSymbol.text = "책 위치 : " + receivedBook.symbol

        binding.btnKyobo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://search.kyobobook.co.kr/search?keyword="+receivedBook.bookName))
            startActivity(intent)
        }

        binding.btnCafe.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://cafe.naver.com/bareumilib"))
            startActivity(intent)
        }

    }
}