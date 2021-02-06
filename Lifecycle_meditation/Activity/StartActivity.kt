package com.example.lifecycle_meditation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.lifecycle_meditation.databinding.ActivityMainBinding
import com.example.lifecycle_meditation.databinding.ActivitySignupBinding
import com.example.lifecycle_meditation.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity(){

    private lateinit var binding: ActivityStartBinding
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"MainActivity - onCreate() called")

        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startbtnStart.setOnClickListener {
            onStartButtonClicked()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"StartActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"StartActivity - onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"StartActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"StartActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"StartActivity - onDestroy() called")

        Log.i(TAG,"log - 좋은하루")
        Toast.makeText(this, "좋은 하루되세요", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"StartActivity - onRestart() called")
    }

    fun onStartButtonClicked(){
        Log.d(TAG,"StartActivity - onStartButtonClicked() called")
    }

}
