package com.example.api.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.api.MainActivity
import com.example.api.MyContext
import com.example.api.R
import com.example.api.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    val TAG: String = "로그"

    // 뷰 바인딩
    private lateinit var binding: ActivityBottomNavigationBinding

    // fragment
    private lateinit var homeFragment: HomeFragment
    private lateinit var settingFragment: SettingFragment
    private lateinit var libraryFragment: LibraryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // context 가져오기
        MyContext.setContext(this)

        // 시작화면 homeFragment
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fl_container, homeFragment).commit()

        // bottom navigation
        binding.bottomNavigation.setOnNavigationItemSelectedListener {  item ->
            when (item.itemId) {
                R.id.page_home -> {
//                    homeFragment = HomeFragment.newInstance()
//                    changeFragment(homeFragment)
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.page_search -> {
//                    searchFragment = SearchFragment.newInstance()
//                    changeFragment(searchFragment)
                    startActivity(Intent(this,SearchActivity::class.java))
                    true
                }
                R.id.page_library -> {
                    libraryFragment = LibraryFragment.newInstance()
                    changeFragment(libraryFragment)
                    true
                }
                R.id.page_setting -> {
                    settingFragment = SettingFragment.newInstance()
                    changeFragment(settingFragment)
                    true
                }
                else -> false
            }
            true
        }

    }


    fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment).commit()
    }
}