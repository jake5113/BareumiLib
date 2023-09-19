package com.jake5113.bareumilib

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

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
    var imgUrl: String? = "https://cdn.pixabay.com/photo/2020/03/27/17/03/shopping-4974313__340.jpg",
    var tag: String
) : Parcelable, Serializable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(bookName)
            writeString(symbol)
            writeString(iSBN)
            writeString(author)
            writeString(mainWord)
            writeString(page)
            writeString(publisher)
            writeString(publishYear)
            writeString(registerNumber)
            writeString(imgUrl)
            writeString(tag)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BooksItem> {
        override fun createFromParcel(parcel: Parcel): BooksItem {
            return BooksItem(parcel)
        }

        override fun newArray(size: Int): Array<BooksItem?> {
            return arrayOfNulls(size)
        }
    }
}
