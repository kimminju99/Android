package com.example.listview_daangn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.listview_daangn.databinding.ListviewItemBinding

class MyListViewAdapter(val context: Context, val modelList:ArrayList<MyModel>) : BaseAdapter(){

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
        // item 받아오기
        binding = ListviewItemBinding.inflate(inflater,parent,false)

        val image = binding.imgMyListViewAdapter
        val title = binding.tvTitleMyListViewAdapter
        val place = binding.tvPlaceMyListViewAdapter
        val price = binding.tvPriceMyListViewAdapter


        val item = modelList[position]

        // 각 item adapter에 연결하기
        image.setImageResource(item.image)
        title.text = item.title
        place.text = item.place
        price.text = item.price

        return binding.root
    }



}