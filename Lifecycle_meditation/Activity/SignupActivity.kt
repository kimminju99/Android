package com.example.lifecycle_meditation

import android.R
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecycle_meditation.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {

    val TAG: String = "로그"
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"SignupActivity - onCreate() called")

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBack.setOnClickListener {
            onBackButtonClicked(view)
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"SignupActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"SignupActivity - onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"SignupActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"SignupActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"SignupActivity - onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"SingupActivity - onRestart() called")
    }

    fun onBackButtonClicked(view: View){
        Log.d(TAG,"SignupActivity - onBackButtonClicked() called")
        finish()
    }


}
