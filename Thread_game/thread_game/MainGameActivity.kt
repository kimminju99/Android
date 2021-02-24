package com.example.thread_game

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import android.view.View.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thread_game.databinding.ActivityMainGameBinding
import javax.net.ssl.HandshakeCompletedEvent


class MainGameActivity : AppCompatActivity() {
    val TAG: String = "로그"
    private lateinit var binding: ActivityMainGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvSwipeMainGame.setOnClickListener {
            //Toast.makeText(this,"Gesture detected", Toast.LENGTH_SHORT).show()
            //var swipe = OnSwipeTouchListener(this)

            //게임 시작
            gameStart()

        }
    }

    fun gameStart(){
        Log.d(TAG,"MainGameActivity - gameStart() called")
        binding.tvLetsMainGame.visibility = VISIBLE
        binding.titleMainGame.visibility = GONE
        binding.lottieSwipeMainGame.visibility = GONE
        binding.tvSwipeMainGame.visibility = GONE
        binding.flInfoMainGame.visibility = VISIBLE

        var handler:Handler = Handler(Looper.myLooper()!!)
        var r = Runnable {
            binding.tvLetsMainGame.visibility = GONE
        }
        handler.postDelayed(r,1000)
    }
}