package com.example.lifecycle_meditation

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecycle_meditation.databinding.ActivityLoginBinding
import java.util.concurrent.Delayed


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "LoginActivity - onCreate() called")

        binding = ActivityLoginBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.signBtnInLogin.setOnClickListener{
            onSignupButtonClicked(view)
        }

        binding.btnBack.setOnClickListener {
            onBackButtonClicked(view)
        }

        binding.loginBtn.setOnClickListener {
            LoginButtonClicked(view)
        }

        binding.facebookBtnLogin.setOnClickListener {
            facebookButtonClicked(view)
        }
        binding.googleBtnLogin.setOnClickListener {
            GoogleButtonClicked(view)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "LoginActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "LoginActivity - onResume() called")


    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "LoginActivity - onPause() called")

        saveData()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "LoginActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "LoginActivity - onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "LoginActivity - onRestart() called")
        loadData()
    }

    fun onSignupButtonClicked(view: View){
        Log.d(TAG, "LoginActivity - onSignupButtonClicked() called")

        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    fun onBackButtonClicked(view: View){
        Log.d(TAG, "LoginActivity - onBackButtonClicked() called")
        finish()
    }

    fun LoginButtonClicked(view: View){
        Log.d(TAG, "LoginActivity - LoginButtonClicked() called")


        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    fun loadData(){
        val pref = getSharedPreferences("pref",0)
        binding.editTextTextEmailAddress.setText(pref.getString("email",""))
        binding.editTextTextPassword.setText(pref.getString("password",""))
    }

    fun saveData(){
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        edit.putString("email", binding.editTextTextEmailAddress.text.toString())
        edit.putString("password",binding.editTextTextPassword.text.toString())
        edit.apply() // 값 저장

    }

    fun facebookButtonClicked(view: View){
        Log.d(TAG, "LoginActivity - facebookButtonClicked() called")
    }
    fun GoogleButtonClicked(view: View){
        Log.d(TAG, "LoginActivity - GoogleButtonClicked() called")
        AlertContinueLink(view)
    }

    fun AlertContinueLink(view: View){
        Log.d(TAG, "LoginActivity - AlertContinueLink() called")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Continue with Google").setMessage("Google로부터 계정을 가져옵니다..")

        val alertDialog = builder.create()

        alertDialog.show()
    }
}
