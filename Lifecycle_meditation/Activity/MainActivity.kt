package com.example.lifecycle_meditation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.lifecycle_meditation.databinding.ActivityLoginBinding.bind
import com.example.lifecycle_meditation.databinding.ActivityLoginBinding.inflate
import com.example.lifecycle_meditation.databinding.ActivityMainBinding

import com.example.lifecycle_meditation.databinding.ActivityStartBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"StartActivity - onCreate() called")

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

        if(getData()){
            intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

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
        removeData()
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

    fun getData():Boolean{
        val account = getSharedPreferences("account",0)
        val name = account.getString("name","")
        val email = account.getString("email","")
        val password = account.getString("password","")

        Log.d(TAG,"MainActivity - onStart() - getData() called : "+ name + " " +email + " " + password)

        if (name.equals("") || email.equals("") || password.equals("")){
            return false
        }
        else{
            return true
        }
    }

    fun removeData(){
        val account = getSharedPreferences("account",0)
        val edit = account.edit()
        edit.clear()
    }
}
