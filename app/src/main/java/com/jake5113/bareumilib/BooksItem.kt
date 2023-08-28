package com.jake5113.bareumilib

data class BooksResponse(
    val books: List<BooksItem>
)

data class BooksItem(
    var bookName: String,
    var symbol: String,
    var iSBN: String? = "",
    var author: String? = "",
    var mainWord: String? = "",
    var page: String? = "페이지 없음",
    var publisher: String? = "",
    var publishYear: String? = "",
    var registerNumber: String,
    var imgUrl: String? = "https://cdn.pixabay.com/photo/2020/03/27/17/03/shopping-4974313__340.jpg"
)
