package com.example.lifecycle_meditation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.lifecycle_meditation.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityHomeBinding
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"HomeActivity - onCreate() called")

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logoutBtnHome.setOnClickListener {
            LogoutButtonClicked()
        }

    }

    fun LogoutButtonClicked(){
        removeData()

        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun removeData(){
        val account = getSharedPreferences("account",0)
        val edit = account.edit()
        edit.clear()
        edit.commit()
    }


}
