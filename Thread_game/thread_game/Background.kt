package com.example.thread_game

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Background(val screenX: Int,val screenY: Int, res: Resources) {
    var x: Int = 0
    var y: Int = 0
    var bg:Bitmap

    init {
        bg = BitmapFactory.decodeResource(res,R.drawable.background)
        bg = Bitmap.createScaledBitmap(bg,this.screenX,this.screenY,true)
    }


}