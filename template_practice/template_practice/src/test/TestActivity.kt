package com.example.template_practice.src.test

import android.os.Bundle
import com.example.template_practice.config.BaseActivity
import com.example.template_practice.databinding.ActivityTestBinding

class TestActivity :BaseActivity<ActivityTestBinding>(ActivityTestBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showCustomToast("환영합니다")
    }
}