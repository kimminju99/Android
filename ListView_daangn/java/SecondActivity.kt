package com.example.listview_daangn

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.listview_daangn.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    val TAG: String = "로그"
    private lateinit var binding: ActivitySecondBinding

    var isLiked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "SecondActivity - onCreate() called")
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 좋아요 버튼 상태 저장 (현재 좋아하지 않는 상태)
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        isLiked = pref.getBoolean("isLiked",false)

        Log.d(TAG,"SecondActivity - isLiked : ${isLiked}")
        edit.putBoolean("isLiked",isLiked)
        edit.apply()

        // 좋아요 상태면
        if (isLiked){
            val animator = ValueAnimator.ofFloat(0.5f, 0.5f).setDuration(1000)
            animator.addUpdateListener { animation: ValueAnimator ->
                binding.btnLikeSecond.setProgress(animation.getAnimatedValue() as Float)
            }
            animator.start()
        }
        // 좋아하지 않는 상태면
        else{
            val animator = ValueAnimator.ofFloat(1f, 1f).setDuration(1000)
            animator.addUpdateListener { animation: ValueAnimator ->
                binding.btnLikeSecond.setProgress(animation.getAnimatedValue() as Float)
            }
            animator.start()
        }

        // 데이터 받아오기
        var readData = intent.extras
        var image = readData?.getInt("content_image", 0)
        var title = readData?.getString("content_title", "")
        var place = readData?.getString("content_place", "")
        var price = readData?.getString("content_price", "")
        var content = readData?.getString("content_content", "")

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
            Log.d(TAG, "SecondActivity - back button clicked")
            finish()
        }

        // 좋아요 버튼
        binding.btnLikeSecond.setOnClickListener {
            // 좋아요 상태 아닐 때 -> 좋아요 상태로
            if (isLiked == false) {
                isLiked = true
                // Custom animation speed or duration.
                val animator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(1000)
                animator.addUpdateListener { animation: ValueAnimator ->
                    binding.btnLikeSecond.setProgress(animation.getAnimatedValue() as Float)
                }
                animator.start()
            }
            // 좋아요 상태 -> 좋아하지 않는 상태
            else{
                isLiked = false
                // Custom animation speed or duration.
                val animator = ValueAnimator.ofFloat(0.5f, 1f).setDuration(1000)
                animator.addUpdateListener { animation: ValueAnimator ->
                    binding.btnLikeSecond.setProgress(animation.getAnimatedValue() as Float)
                }
                animator.start()
            }

            // 다음에 왔을 때도 상태 저장되어있길
            edit.putBoolean("isLiked",isLiked)
            edit.apply()
            Log.d(TAG,"SecondActivity - ClickListener - isLiked : ${isLiked}")
        }



    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"SecondActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"SecondActivity - onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"SecondActivity - onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"SecondActivity - onRestart() called")
    }


}