package com.example.api_book.recyclerview


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_book.App
import com.example.api_book.MyContext
import com.example.api_book.R
import com.example.api_book.databinding.LayoutItemBinding


class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //val inflater = itemView as LayoutInflater
    var binding = LayoutItemBinding.inflate(LayoutInflater.from(MyContext.context))

    // 뷰 가져오기
    private val image = binding.bookImgItem
    private val title = binding.titleItem
    private val author = binding.authorItem


    // 데이터와 뷰 묶기
    fun bindWithView(bookitem:Book){
        title.text = bookitem.title
        author.text = bookitem.author
        // 이미지 설정
        Glide.with(MyContext.context)
            .load(bookitem.image)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(image)
    }

}