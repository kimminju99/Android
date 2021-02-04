package com.example.lifecycle_meditation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.lifecycle_meditation.databinding.ActivityPolicyBinding

class PolicyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPolicyBinding
    val TAG: String = "로그"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"PolicyActivity - onCreate() called")

        binding = ActivityPolicyBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.agreeBtn.setOnClickListener {
            onAgreeButtonClicked(view)
        }

        binding.disagreeBtn.setOnClickListener {
            onDisagreeButtonClicked(view)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"PolicyActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"PolicyActivity - onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"PolicyActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"PolicyActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"PolicyActivity - onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"PolicyActivity - onRestart() called")
    }

    fun onDisagreeButtonClicked(view: View){
        Log.d(TAG,"PolicyActivity - onDisagreeButtonClicked() called")
        finish()
    }

    fun onAgreeButtonClicked(view: View){
        Log.d(TAG,"PolicyActivity - agreeButtonClicked() called")
        finish()
        //check box 수정
    }

}
