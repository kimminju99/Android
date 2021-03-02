package com.example.thread_game

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Leaf(res:Resources) {

    var leaf = BitmapFactory.decodeResource(res,R.drawable.leaf)
    var width = leaf.width
    var height = leaf.height

    var speed = 20
    var x=0
    var y=-20

    init {
        width /=4
        height /=4
        leaf = Bitmap.createScaledBitmap(leaf,width,height,false)
        //y=height
    }

    @JvmName("getLeaf1")
    fun getLeaf(): Bitmap? {
        return leaf
    }

    fun getCollisionShape(): Rect {
        return Rect(x,y,x+width,y+height)
    }

}