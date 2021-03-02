package com.example.thread_game

import android.graphics.Canvas
import android.os.Build
import android.util.Log
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi

//화면 만드는데 필요한 계산, 화면 그리는 thread
class MainThread(var surfaceHolder: SurfaceHolder, var gameView: GameView): Thread() {
    val TAG: String = "로그"

    var VIEW_WIDTH:Int = 0
    var VIEW_HEIGHT:Int = 0
    private var running:Boolean = false
    lateinit var canvas: Canvas


    @RequiresApi(Build.VERSION_CODES.O)
    override fun run() {
        super.run()
        while(running){
            //Log.d(TAG,"MainThread - run()  - running ${running}")
            canvas = this.surfaceHolder.lockCanvas()
            VIEW_WIDTH = canvas.width
            VIEW_HEIGHT =canvas.height
            synchronized(surfaceHolder, {
                gameView.update(VIEW_WIDTH,VIEW_HEIGHT)
                gameView.Mydraw(canvas)
            })
            surfaceHolder.unlockCanvasAndPost(canvas)
            }

        }

    fun setRunning(isRunning:Boolean){
        running = isRunning
    }

    fun getRunning(): Boolean {
        return running
    }


}