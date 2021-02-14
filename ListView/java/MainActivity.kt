package com.example.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.listview.databinding.ActivityMainBinding
import com.example.listview.databinding.PlaylistItemBinding
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    val TAG: String = "로그"

    private lateinit var binding: ActivityMainBinding
    private  lateinit var bindingItm:PlaylistItemBinding

    var MusicList = arrayListOf<MusicItm>()

    var turnOnFragment: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d(TAG,"MainActivity - onCreate() called")

        for (i in 1..10) {
            addData()
        }

        val Adapter = MusicItmAdaptor(this, MusicList)
        binding.listViewMain.adapter = Adapter



        binding.listViewMain.setOnItemLongClickListener { parent, view, position, id ->
            Log.d(TAG,"MainActivity - long Item Clicked")

            //Todo dot버튼 클릭시 fragment 나오게
            turnFragment(true)
            return@setOnItemLongClickListener true
        }



    }



    fun addData(){
        MusicList.add(MusicItm(R.drawable.ic_play, "Celebrity", "아이유"))
        MusicList.add(MusicItm(R.drawable.ic_play, "It's All Right", "Jon Batiste"))
        MusicList.add(MusicItm(R.drawable.ic_play, "꽃길", "세정"))
        MusicList.add(MusicItm(R.drawable.ic_play, "One In A Million", "SURAN(수란)"))
        MusicList.add(
            MusicItm(
                R.drawable.ic_play,
                "사라진 모든 것들에게(with ELLE KOREA)",
                "코드 쿤스트(CODE KUNST), 잔나비 최정훈, 사이먼 도미닉"
            )
        )
        MusicList.add(MusicItm(R.drawable.ic_play, "반짝 빛나던, 나의 2006년", "적재"))
        MusicList.add(MusicItm(R.drawable.ic_play, "그대를 만나고", "데이무드"))
        MusicList.add(MusicItm(R.drawable.ic_play, "STAR", "제시 (Jessi)"))
    }

    override fun onPause() {
        Log.d(TAG,"MainActivity - onPause() called")
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MainActivity - onDestroy() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"MainActivity - onStop() called")
    }

    fun turnFragment(detail:Boolean){
        val fm = supportFragmentManager
        val fmTransaction = fm.beginTransaction()
        val fragment = FragmentDetail()
        if(detail){
            fmTransaction.add(R.id.fragment_main,fragment)
        }
        else{
            fmTransaction.hide(fragment)
        }
        fmTransaction.commit()
    }

}

