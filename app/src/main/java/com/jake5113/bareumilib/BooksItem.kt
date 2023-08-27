package com.jake5113.bareumilib

data class BooksResponse(
    val books: List<BooksItem>
)

data class BooksItem(
    val author: String? = "",
    val bookName: String,
    val iSBN: String? = "",
    val mainWord: String? = "",
    val page: String? = "",
    val publishYear: String? = "",
    val publisher: String? = "",
    val registerNumber: String,
    val symbol: String
)

