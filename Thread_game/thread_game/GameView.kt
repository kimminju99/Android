package com.example.thread_game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.SurfaceView

class GameView(context: Context?,var screenX:Int,var screenY:Int) : SurfaceView(context), Runnable{

    private lateinit var thread:Thread
    private var isPlaying : Boolean=true

    private var background1:Background
    private var background2 :Background


    init {
        background1 = Background(screenX,screenY,resources)
        background2 = Background(screenX,screenY,resources)
        background2.x = screenX
    }

    override fun run() {
        update()
        draw()
    }

    fun update(){
        var screenRationX = 1920f/screenX
        var screenRationY = 1020f/screenY

        background1.x -= (10 * screenRationX) as Int
        background2.x -= (10 *screenRationY) as Int

        if (background1.x + background1.bg.width < 0){
            background1.x = screenX
        }
        if (background2.x + background2.bg.width < 0){
            background2.x = screenX
        }
    }

    fun draw(){
        var paint:Paint = Paint()
        var canvas:Canvas

        if (holder.surface.isValid){
            canvas = holder.lockCanvas()
            canvas.drawBitmap(background1.bg, background1.x.toFloat(), background1.y.toFloat(),paint)
            canvas.drawBitmap(background2.bg, background2.x.toFloat(), background2.y.toFloat(),paint)
            holder.unlockCanvasAndPost(canvas)
        }

    }

    fun resume(){
        isPlaying=true
        thread.start()
    }

    fun pause(){
        isPlaying=false
        thread.join()
    }
}