package com.jake5113.bareumilib

// TODO: XML 파싱중
data class Channel(
    val channel : Items
)
data class Items(
    val items: ArrayList<NaverBooksItem>
)

data class NaverBooksItem(
    val author: String = "",
    val description: String = "",
    val discount: String = "",
    val image: String = "",
    val isbn: String = "",
    val link: String = "",
    val price: String = "",
    val pubdate: String = "",
    val publisher: String = "",
    val title: String = ""
)
