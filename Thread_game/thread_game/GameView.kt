package com.example.thread_game

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.os.Build
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getColor
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
class GameView(activity: Activity, context: Context?)
    : SurfaceView(context), SurfaceHolder.Callback {
    val TAG: String = "로그"

    var thread: MainThread

    // 리소스
    var mChar: Moving = Moving(resources)
    var background = BitmapFactory.decodeResource(resources, R.drawable.yellow_background)
    var life_icon = BitmapFactory.decodeResource(resources, R.drawable.giraffe_icon)
    var life = 2

    //화면 크기
    private var VIEW_WIDTH = 0
    private var VIEW_HIEGHT = 0


    // 떨어지는 나뭇잎
    private val leafList = ArrayList<Leaf>()

    // 점수
    private var score =0
    var prefs:SharedPreferences



    init {
        holder.addCallback(this)
        thread = MainThread(holder, this)
        isFocusable = true
        for (i in 1..4) {
            leafList.add(Leaf(resources))
        }
        life_icon = Bitmap.createScaledBitmap(
            life_icon,
            life_icon.width / 16,
            life_icon.height / 16,
            false
        )
        prefs= activity.getSharedPreferences("score", 0)
    }


    fun update(width: Int, height: Int) {
        VIEW_WIDTH = width
        VIEW_HIEGHT = height

        //Log.d(TAG,"GameView - update() - (x,y) : (${x},${y})")

        for (leaf in leafList) {
            moveLeafs(leaf)
            if (Rect.intersects(leaf.getCollisionShape(), mChar.getCollisonShape())) {
                //Log.d(TAG,"GameView - update() 만났당!")
                score++
                leaf.y=-20
                leaf.speed = 20
            }
        }


    }

    fun moveLeafs(leaf: Leaf) {
        //Log.d(TAG, "GameView - moveLeafs() - leaf: (${leaf.x},${leaf.y})")
        leaf.y += leaf.speed

        if (leaf.y <= 0) {
            leaf.x = Random.nextInt(VIEW_WIDTH - leaf.width)
            leaf.speed = Random.nextInt(5, 20)
        }

        if (leaf.y + leaf.height > VIEW_HIEGHT) {
            // 계속 leaf 나오게
            leaf.y = -20
            leaf.speed = 20

            gameOver()
            // 목숨 3개
            //reduceLife()
            // 목숨 다 쓰면 게임 종료
            if (life <0) {
                Log.d(TAG, "GameView - moveLeafs() - life : ${life}")
                //gameOver()
            }
        }

    }

    fun reduceLife(){
        life--
        thread.setRunning(false)
        thread.join()

        Log.d(TAG, "GameView - reduceLife() - life : ${life}")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        var newX = 0
        var newY = 0
        var dX = 0f
        var dY = 0

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                dX = rootView.x - event.rawX
                //dY = (rootView.y - event.rawY).toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                newX = (event.rawX + dX).toInt()
                //newY = (event.rawY + dY).toInt()

                mChar.x = newX
                //mChar.y = newY
            }
            MotionEvent.ACTION_UP -> {
                if (thread.getRunning() == false) {
                    Log.d(TAG, "GameView - onTouchEvent() called - 다시 스레드 시작")
                    holder.addCallback(this)
                    thread = MainThread(holder, this)
                    thread.setRunning(true)
                    thread.start()
                }
            }
        }

        //Log.d(TAG,"GameView - onTouchEvent() - mChar (${mChar.x},${mChar.y})")
        return true
    }

    fun gameOver(){
        Log.d(TAG,"GameView - gameOver() called")
        thread.setRunning(false)
        thread.join()

        // 다이얼로그 나오게
//       var dialog = MyCustomDialog(context)
//        var handler = Handler(Looper.myLooper()!!)
//        handler.postDelayed({ dialog.show() }, 3000)


    }


    fun Mydraw(canvas: Canvas) {
        super.draw(canvas)

        var paint = Paint()
        var textPaint = Paint()
        var dest = Rect(0, 0, width, height)

        if (holder.surface.isValid) {
            //Log.d(TAG, "GameView - draw() called - ${holder.surface.isValid}")
            canvas.drawColor(Color.WHITE)
            paint.isFilterBitmap = true
            // 배경
            canvas.drawBitmap(background, null, dest, paint)
            // 기린
            canvas.drawBitmap(
                mChar.getMovingCharacter(),
                mChar.x.toFloat(),
                mChar.y.toFloat(),
                paint
            )

            // 나뭇잎
            for (leaf in leafList) {
                leaf.getLeaf()
                    ?.let { canvas.drawBitmap(it, leaf.x.toFloat(), leaf.y.toFloat(), paint) }
            }

            // 점수 표시
            textPaint.typeface = resources.getFont(R.font.caveat)
            textPaint.textSize = 80F
            textPaint.color = getColor(context, R.color.black)
            canvas.drawText("Score : " + score, 50f, 130f, textPaint)

            // 목숨 표시
            canvas.drawBitmap(life_icon, 840f, 50f, paint)
            canvas.drawText(" x " + life, 900f, 130f, textPaint)

        }
    }



    override fun surfaceCreated(holder: SurfaceHolder) {
        //Log.d(TAG, "GameView - surfaceCreated() called")
        thread.setRunning(true)
        thread.start()

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d(TAG, "GameView - surfaceChanged() called")
//        var retry = true
//        while (retry) {
//            thread.setRunning(false)
//            thread.join()
//            retry = false
//        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d(TAG, "GameView - surfaceDestroyed() called")
    }

    fun resume() {
        Log.d(TAG, "GameView - resume() called")
        if (thread == null && holder.surface.isValid) {
            Thread().start()
        }
    }

    fun pause() {
        thread.join()
    }


}