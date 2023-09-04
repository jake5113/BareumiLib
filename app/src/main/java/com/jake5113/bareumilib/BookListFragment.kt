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
    ): View {
        binding = FragmentBookListBinding.inflate(inflater, container, false)

        return binding.root
    }
    fun setBooksList(books: List<BooksItem>) {
        binding.booksRecycler.adapter = BookRecyclerAdapter(requireContext(), books)
    }
}