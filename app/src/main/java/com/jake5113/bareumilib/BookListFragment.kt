package com.jake5113.bareumilib

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jake5113.bareumilib.databinding.FragmentBookListBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookListFragment : Fragment() {
    lateinit var binding : FragmentBookListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookListBinding.inflate(inflater, container, false)

        // 테스트 코드
/*        val books = arrayListOf<BooksItem>(
            BooksItem(bookName = "AAAAAAAAAAAA", symbol = "A", registerNumber = "A"),
            BooksItem(bookName = "BBBBBBB", symbol = "B", registerNumber = "B"),
            BooksItem(bookName = "CCC", symbol = "C", registerNumber = "C"),
            BooksItem(bookName = "DD", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "E", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "F", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "G", symbol = "D", registerNumber = "A"),
            BooksItem(bookName = "HABCDEFGHIJKLMNOPQRST", symbol = "D", registerNumber = "A"),
        )*/

        val books = (activity as MainActivity).booksList

        binding.booksRecycler.adapter = BookRecyclerAdapter(requireContext(),books)

        return binding.root
    }
}