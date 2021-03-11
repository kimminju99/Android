package com.example.api_book.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_book.MyContext
import com.example.api_book.R

class RecyclerViewAdapter: RecyclerView.Adapter<ItemViewHolder>() {

    private var itemList = ArrayList<Book>()

    // 뷰 홀더와 레이아웃 연결
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(MyContext.context).inflate(R.layout.layout_item,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<Book>){
        this.itemList = list
        notifyDataSetChanged()
    }
}


