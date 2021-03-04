package com.example.api_book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.api_book.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val r = Runnable { goMainGame() }
        var handler : Handler
        handler = Handler(Looper.myLooper()!!)

        // 3초 후 자동 game 화면으로
        handler.postDelayed(r,3000)

        // 화면 누르면 game화면으로
        binding.spashScreenMain.setOnClickListener {
            handler.removeCallbacks(r)
            goMainGame()
        }
    }

    fun goMainGame(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
