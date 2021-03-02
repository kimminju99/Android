package com.example.thread_game

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class MyCallback : SurfaceHolder.Callback {
    val TAG: String = "로그"

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.d(TAG,"MyCallback - surfaceCreated() called")
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d(TAG,"MyCallback - surfaceChanged() called")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d(TAG,"MyCallback - surfaceDestroyed() called")
    }
}