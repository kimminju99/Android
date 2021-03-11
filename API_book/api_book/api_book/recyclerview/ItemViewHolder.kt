package com.example.api_book.recyclerview


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_book.App
import com.example.api_book.MyContext
import com.example.api_book.R
import com.example.api_book.databinding.LayoutItemBinding
import com.example.api_book.utils.Constant.TAG


class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val image = itemView.findViewById<ImageView>(R.id.bookImg_Item)
    private val title = itemView.findViewById<TextView>(R.id.title_Item)
    private val author = itemView.findViewById<TextView>(R.id.author_Item)


    // 데이터와 뷰 묶기
    fun bindWithView(bookitem:Book){
        title.text = bookitem.title
        author.text = bookitem.author
        // 이미지 설정
        Glide.with(MyContext.context)
            .load(bookitem.image)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(image)

        //Log.d(TAG,"ItemViewHolder - bindWithView()")

    }

}