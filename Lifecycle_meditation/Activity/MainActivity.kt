package com.example.lifecycle_meditation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lifecycle_meditation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"MainActivity - onCreate() called")

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        binding.signBtn.setOnClickListener{
            onSignupButtonClicked()
        }
        binding.loginBtn.setOnClickListener{
            onLoginButtonClicked()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"MainActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"MainActivity - onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"MainActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"MainActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MainActivity - onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"MainActivity - onRestart() called")
    }

    fun onSignupButtonClicked(){
        Log.d(TAG,"MainActivity - onSignupButtonClicked() called")

        val intent = Intent(this,SignupActivity::class.java)
        startActivity(intent)

    }

    fun onLoginButtonClicked(){
        Log.d(TAG,"MainActivity - onLoginButtonClicked() called")

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }






}
