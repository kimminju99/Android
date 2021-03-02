package com.example.thread_game

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import android.view.View.*
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.thread_game.databinding.ActivityMainGameBinding
import javax.net.ssl.HandshakeCompletedEvent


class MainGameActivity : AppCompatActivity() {
    val TAG: String = "로그"
    private lateinit var binding: ActivityMainGameBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Swipe to Start 클릭시 진짜 게임 시작
        binding.flSwipeMainGame.setOnClickListener {
            //Toast.makeText(this,"Gesture detected", Toast.LENGTH_SHORT).show()
            //var swipe = OnSwipeTouchListener(this)
            //게임 시작
            gameStart()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun gameStart(){
        Log.d(TAG,"MainGameActivity - gameStart() called")
        binding.tvLetsMainGame.visibility = VISIBLE
        binding.titleMainGame.visibility = GONE
        binding.lottieSwipeMainGame.visibility = GONE
        binding.flSwipeMainGame.visibility = GONE
        //binding.flInfoMainGame.visibility = VISIBLE

        // Let's Start! 보여주고 게임 캐릭터 등장&게임화면
        var handler:Handler = Handler(Looper.myLooper()!!)
        var r = Runnable {
            binding.tvLetsMainGame.visibility = GONE
            //binding.giraffeMainGame.visibility = VISIBLE
            var gameview = GameView(this,this)
            setContentView(gameview)
            //startActivity(Intent(this, GameActivity::class.java))
        }
        handler.postDelayed(r,1000)

    }


}