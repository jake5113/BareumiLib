package com.jake5113.bareumilib

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel: ViewModel() {
    val booksData = MutableLiveData<BooksItem>()

    fun updateData(newData: BooksItem) {
        booksData.value = newData
    }
}