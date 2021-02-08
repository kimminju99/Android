package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var MusicList = arrayListOf<MusicItm>(
        MusicItm(R.drawable.ic_play, "Celebrity", "아이유"),
        MusicItm(R.drawable.ic_play, "It's All Right", "Jon Batiste"),
        MusicItm(R.drawable.ic_play, "꽃길", "세정"),
        MusicItm(R.drawable.ic_play, "One In A Million", "SURAN(수란)"),
        MusicItm(
            R.drawable.ic_play,
            "사라진 모든 것들에게(with ELLE KOREA)",
            "코드 쿤스트(CODE KUNST), 잔나비 최정훈, 사이먼 도미닉"
        ),
        MusicItm(R.drawable.ic_play, "반짝 빛나던, 나의 2006년", "적재"),
        MusicItm(R.drawable.ic_play, "그대를 만나고", "데이무드"),
        MusicItm(R.drawable.ic_play, "STAR", "제시 (Jessi)"),
        MusicItm(R.drawable.ic_play, "Celebrity", "아이유"),
        MusicItm(R.drawable.ic_play, "It's All Right", "Jon Batiste"),
        MusicItm(R.drawable.ic_play, "꽃길", "세정"),
        MusicItm(R.drawable.ic_play, "One In A Million", "SURAN(수란)"),
        MusicItm(
            R.drawable.ic_play,
            "사라진 모든 것들에게(with ELLE KOREA)",
            "코드 쿤스트(CODE KUNST), 잔나비 최정훈, 사이먼 도미닉"
        ),
        MusicItm(R.drawable.ic_play, "반짝 빛나던, 나의 2006년", "적재"),
        MusicItm(R.drawable.ic_play, "그대를 만나고", "데이무드"),
        MusicItm(R.drawable.ic_play, "STAR", "제시 (Jessi)")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val Adapter = MusicItmAdaptor(this, MusicList)
        binding.listViewMain.adapter = Adapter
    }
}
