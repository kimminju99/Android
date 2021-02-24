package com.example.thread_game

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class OnSwipeTouchListener(val context:Context): View.OnTouchListener {
    var gestureDetector = GestureDetector(context,SwipeGestureDetector())

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
}