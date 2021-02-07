package com.example.lifecycle_meditation

import android.R
import android.content.Intent
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
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.CompoundButtonCompat
import com.example.lifecycle_meditation.databinding.ActivitySignupBinding



class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"SignupActivity - onCreate() called")

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBack.setOnClickListener {
            onBackButtonClicked(view)
        }


        binding.policyTxt.setOnClickListener {
            onPolicyButtonClicked(view)
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
        // policyCheck로부터 Boolean값 받아오기
        val pref = getSharedPreferences("policyCheck", 0)
        val checking = pref.getBoolean("checked",false)

        Log.v(TAG," SingupActivity - onRestart() - checkbox : "+checking)

        // agree했으면
        if(checking){
            // 체크 박스 체크
            binding.readPolicyCheckbox.isChecked = true
            // 로그인 버튼 클릭 가능하게
            binding.startbtnSignup.setOnClickListener {
                onStartButtonClicked(binding.root)
            }
        } // disagree 했으면
        else{
            // 체크박스 해제
            binding.readPolicyCheckbox.isChecked= false
        }

    }

    fun onBackButtonClicked(view: View){
        Log.d(TAG,"SignupActivity - onBackButtonClicked() called")
        finish()
    }

    fun onPolicyButtonClicked(view: View){
        Log.d(TAG,"SignupActivity - onPolicyButtonClicked() called")

        val intent = Intent(this, PolicyActivity::class.java)
        startActivity(intent)
    }

    fun onStartButtonClicked(view: View){
        Log.d(TAG,"SignupActivity - onStartButtonClicked() called")

        //자동 로그인을 위해 account에 데이터 저장
        saveData()

        intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


    fun saveData(){
        val account = getSharedPreferences("account",0)
        val edit = account.edit()
        edit.putString("name", binding.editTextTextPersonName.text.toString())
        edit.putString("email", binding.editTextTextEmailAddress.text.toString())
        edit.putString("password",binding.editTextTextPassword.text.toString())
        edit.apply() // 값 저장

    }

}
