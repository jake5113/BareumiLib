package com.jake5113.bareumilib

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jake5113.bareumilib.databinding.ItemBooksBinding


class BookRecyclerAdapter(private var context:Context, private var booksList: List<BooksItem>) : RecyclerView.Adapter<BookRecyclerAdapter.VH>() {
    inner class VH(var binding: ItemBooksBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemBooksBinding.inflate(LayoutInflater.from(context),parent,false))

    override fun getItemCount(): Int = booksList.size
    override fun onBindViewHolder(holder: VH, position: Int) {
        val book = booksList[position]
        Glide.with(context).load( book.imgUrl).into(holder.binding.bookImg)
        holder.binding.bookText.text = book.bookName
    }
}