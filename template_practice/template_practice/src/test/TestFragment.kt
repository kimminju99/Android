package com.example.template_practice.src.test

import android.os.Bundle
import android.view.View
import com.example.template_practice.R
import com.example.template_practice.config.BaseFragment
import com.example.template_practice.databinding.FragmentTestBinding

class TestFragment:BaseFragment<FragmentTestBinding>(FragmentTestBinding::bind, R.layout.fragment_test) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCustomToast("안녕하세요")
    }
}