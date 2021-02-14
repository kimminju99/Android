package com.example.listview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.listview.databinding.PlaylistItemBinding


class MusicItmAdaptor(val context: Context, val MusicList:ArrayList<MusicItm>): BaseAdapter() {

    val TAG: String = "로그"

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: PlaylistItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = PlaylistItemBinding.inflate(inflater,parent,false)

        // MusicList 받아오기
        val title = binding.tvTitlePlayList
        val time = binding.tvNamePlayList

        val music = MusicList[position]

        title.text = music.title
        time.text = music.name



        // playList 클릭 가능케
        binding.btnDotPlayList.setOnClickListener {
            Toast.makeText(context,"Dot Button",Toast.LENGTH_SHORT).show()
            Log.d(TAG,"MusicItmAdaptor - getView() called")

        }



        return binding.root
    }

    override fun getCount(): Int {
        return MusicList.size
    }

    override fun getItem(position: Int): Any {
        return MusicList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }


}
