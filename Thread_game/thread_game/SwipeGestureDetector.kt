package com.example.thread_game

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent


class SwipeGestureDetector() : GestureDetector.SimpleOnGestureListener() {
    val TAG: String = "로그"

    private val SWIPE_THRESHOLD = 100
    private val SWIPE_VELOCITY_THRESHOLD = 100

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        var diffY = Math.abs(e1!!.y - e2!!.y)
        var diffX= Math.abs(e1!!.x - e2!!.x)
        var result = false
        Log.d(TAG,"SwipeGestureDetector - onFling() called")

        if (diffX>diffY){
            if(diffX > SWIPE_THRESHOLD && Math.abs(velocityX)> SWIPE_VELOCITY_THRESHOLD ){
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        }

        return result
    }

    fun onSwipeLeft(){

    }

    fun onSwipeRight(){
        Log.d(TAG,"SwipeGestureDetector - onSwipeRight() called")
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {

    }

}