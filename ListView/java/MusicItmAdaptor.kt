package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.listview.databinding.PlaylistItemBinding


class MusicItmAdaptor(val context: Context, val MusicList : ArrayList<MusicItm>): BaseAdapter() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: PlaylistItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = PlaylistItemBinding.inflate(inflater,parent,false)

        val title = binding.tvTitlePlayList
        val time = binding.tvNamePlayList

        val music = MusicList[position]

        title.text = music.title
        time.text = music.name

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
