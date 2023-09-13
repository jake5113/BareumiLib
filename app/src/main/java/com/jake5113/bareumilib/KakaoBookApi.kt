package com.jake5113.bareumilib

data class KakaoBookApi(
    val documents: List<Document>?,
    val meta: Meta = Meta()
)
data class Meta(
    val is_end: Boolean = false,
    val pageable_count: Int = 0,
    val total_count: Int = 0
)
data class Document(
    val authors: List<String>,
    val contents: String = "",
    val datetime: String = "",
    val isbn: String = "",
    val price: Int = 0,
    val publisher: String = "",
    val sale_price: Int = 0,
    val status: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val translators: List<Any> = listOf(),
    val url: String = ""
)