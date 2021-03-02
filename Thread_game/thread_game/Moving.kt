package com.example.thread_game

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.util.Log
import com.example.thread_game.GameView

class Moving(res: Resources) {
    val TAG: String = "로그"

    var character: Bitmap = BitmapFactory.decodeResource(res, R.drawable.giraffe_png)
    var width: Int
    var height: Int
    var x: Int = 0
    var y: Int = 0

    val VIEW_WIDTH = Resources.getSystem().displayMetrics.widthPixels
    val VIEW_HEIGHT = Resources.getSystem().displayMetrics.heightPixels

    init {
        width = character.width/4
        height = character.height/4
        character = Bitmap.createScaledBitmap(character, width, height , false)

        x = 380
        y = VIEW_HEIGHT/2
    }


    fun getMovingCharacter(): Bitmap {
        return character
    }

    fun getCollisonShape(): Rect {
        return Rect(x,y,x+width,y+height)
    }

}