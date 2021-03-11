package com.example.api_book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.api_book.databinding.ActivityBottomNavigationBinding
import com.example.api_book.fragment.*

class BottomNavigationActivity : AppCompatActivity() {
    val TAG: String = "로그"

    // 뷰 바인딩
    private lateinit var binding: ActivityBottomNavigationBinding

    // fragment
    private lateinit var homeFragment: HomeFragment
    private lateinit var libraryFragment: LibraryFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var searchRecyclerFragment: SearchRecyclerFragment
    private lateinit var settingFragment: SettingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 시작화면 homeFragment
        homeFragment = HomeFragment.newInstance()
        //searchRecyclerFragment = SearchRecyclerFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fl_container, homeFragment).commit()
        //supportFragmentManager.beginTransaction().add(R.id.fl_container, searchRecyclerFragment).commit()

        // bottom navigation
        binding.bottomNavigation.setOnNavigationItemSelectedListener {  item ->
            when (item.itemId) {
                R.id.page_home -> {
                    homeFragment = HomeFragment.newInstance()
                    changeFragment(homeFragment)
                    true
                }
                R.id.page_search -> {
                    searchFragment = SearchFragment.newInstance()
                    changeFragment(searchFragment)
                    //startActivity(Intent(this,MainActivity::class.java))
//                    searchRecyclerFragment = SearchRecyclerFragment.newInstance()
//                    changeFragment(searchRecyclerFragment)
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