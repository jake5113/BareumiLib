import com.tickaroo.tikxml.annotation.*

@Xml(name = "rss")
data class RssFeed(
    @Element(name = "channel")
    val channel: Channel
)

data class Channel(
    @Element(name = "title")
    val title: String,

    @Element(name = "link")
    val link: String,

    @Element(name = "description")
    val description: String,

    @Element(name = "item")
    val items: List<BookItem>
)

data class BookItem(
    @Element(name = "title")
    val title: String,

    @Element(name = "link")
    val link: String,

    @Element(name = "image")
    val image: String,

    @Element(name = "author")
    val author: String,

    @Element(name = "price")
    val price: String,

    @Element(name = "discount")
    val discount: String,

    @Element(name = "publisher")
    val publisher: String,

    @Element(name = "pubdate")
    val pubdate: String,

    @Element(name = "isbn")
    val isbn: String,

    @Element(name = "description")
    val description: String
)
