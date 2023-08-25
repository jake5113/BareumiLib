package com.example.bareumilib

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bareumilib.databinding.FragmentBookListBinding

class BookListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookListBinding.inflate(inflater, container, false)

        val books = arrayListOf<BooksItem>(
            BooksItem(bookName = "AAAAAAAAAAAA", symbol = "A", registerNumber = "A"),
            BooksItem(bookName = "BBBBBBB", symbol = "B", registerNumber = "B"),
            BooksItem(bookName = "CCC", symbol = "C", registerNumber = "C"),
            BooksItem(bookName = "DD", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "E", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "F", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "G", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "HABCDEFGHIJKLMNOPQRST", symbol = "D", registerNumber = "A"),
        )

        binding.booksRecycler.adapter = BookRecyclerAdapter(requireContext(),books)

        return binding.root
    }
}