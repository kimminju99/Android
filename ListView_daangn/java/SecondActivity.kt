package com.example.listview_daangn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.listview_daangn.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    val TAG: String = "로그"
    private lateinit var binding:ActivitySecondBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"SecondActivity - onCreate() called")
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 데이터 받아오기
        var readData = intent.extras
        var image = readData?.getInt("content_image",0)
        var title = readData?.getString("content_title","")
        var place = readData?.getString("content_place","")
        var price = readData?.getString("content_price","")
        var content = readData?.getString("content_content","")

        // 데이터 set
        if (image != null) {
            binding.imageViewSecond.setImageResource(image)
        }
        binding.tvTitleSecond.setText(title)
        binding.tvPlaceSecond.setText(place)
        binding.tvPriceSecond.setText(price)
        binding.tvContentSecond.setText(content)



        // 뒤로가기
        binding.btnBack.setOnClickListener {
            Log.d(TAG,"SecondActivity - back button clicked")
            finish()
        }
    }
}
