package com.example.lifecycle_meditation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.lifecycle_meditation.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"LoginActivity - onCreate() called")

        binding = ActivityLoginBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.signBtnInLogin.setOnClickListener{
            onSignupButtonClicked(view)
        }

        binding.btnBack.setOnClickListener {
            onBackButtonClicked(view)
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"LoginActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"LoginActivity - onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"LoginActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"LoginActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"LoginActivity - onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"LoginActivity - onRestart() called")
    }

    fun onSignupButtonClicked(view: View){
        Log.d(TAG,"LoginActivity - onSignupButtonClicked() called")

        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    fun onBackButtonClicked(view: View){
        Log.d(TAG,"LoginActivity - onBackButtonClicked() called")
        finish()
    }
}
