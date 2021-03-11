package com.example.template_practice.src.main.myPage

import android.os.Bundle
import android.view.View
import com.example.template_practice.R
import com.example.template_practice.config.BaseFragment
import com.example.template_practice.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    private var mCount =0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChangeCounterText.setOnClickListener {
            binding.textViewCounter.text = resources.getString(R.string.my_page_tv_counter,++mCount)
        }
    }
}