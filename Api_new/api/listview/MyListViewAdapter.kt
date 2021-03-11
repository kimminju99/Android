package com.example.api.listview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.api.News
import com.example.api.databinding.ListviewItemBinding
import com.example.api.utils.Constant.TAG


class MyListViewAdapter(val context: Context, val modelList:ArrayList<News>) : BaseAdapter(){

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: ListviewItemBinding

    override fun getCount(): Int {
        return modelList.size
    }

    override fun getItem(position: Int): Any {
        return modelList[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d(TAG,"MyListViewAdapter - getView() called")
        // item 받아오기
        binding = ListviewItemBinding.inflate(inflater,parent,false)

        val title = binding.tvTitleItem
        val content = binding.tvContentItem


        val item = modelList[position]

        // 각 item adapter에 연결하기
        title.text = item.title
        content.text = item.description


        return binding.root
    }



}