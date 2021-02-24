package com.example.thread_game

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.WindowMetrics
import androidx.appcompat.app.AppCompatActivity


// showing SurfaceView
class GameActivity : AppCompatActivity() {

    private lateinit var gameview:GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenX = findViewById<View>(R.id.entire_layout_mainGame).width
        val screenY = findViewById<View>(R.id.entire_layout_mainGame).height

        gameview = GameView(this,screenX,screenY)

        setContentView(gameview)
    }

    override fun onPause() {
        super.onPause()
        gameview.pause()
    }

    override fun onResume() {
        super.onResume()
        gameview.resume()
    }
}