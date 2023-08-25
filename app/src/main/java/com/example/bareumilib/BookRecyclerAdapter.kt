package com.example.bareumilib

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.bareumilib.databinding.ItemBooksBinding


class BookRecyclerAdapter(private var context:Context, private var booksList: ArrayList<BooksItem>) : RecyclerView.Adapter<BookRecyclerAdapter.VH>() {
    inner class VH(var binding: ItemBooksBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemBooksBinding.inflate(LayoutInflater.from(context),parent,false))

    override fun getItemCount(): Int = booksList.size ?: 1
    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load( "https://cdn.pixabay.com/photo/2020/03/27/17/03/shopping-4974313__340.jpg").into(holder.binding.bookImg)
        holder.binding.bookText.text = booksList[position].bookName
    }
}