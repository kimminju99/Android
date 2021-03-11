package com.example.template_practice.src.main

import android.app.Activity
import android.os.Bundle
import com.example.template_practice.R
import com.example.template_practice.config.BaseActivity
import com.example.template_practice.databinding.ActivityMainBinding
import com.example.template_practice.src.main.home.HomeFragment
import com.example.template_practice.src.main.myPage.MyPageFragment
import com.example.template_practice.src.test.TestFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity :BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when(item.itemId){
                    R.id.menu_main_btm_nav_home->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm,HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_my_page->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_test->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, TestFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })

    }
}