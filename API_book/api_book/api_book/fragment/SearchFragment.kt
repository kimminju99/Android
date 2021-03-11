package com.example.api_book.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.api_book.MainActivity
import com.example.api_book.R
import com.example.api_book.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    companion object {
        const val TAG: String = "로그"

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    // 뷰 바인딩
    private lateinit var binding: FragmentSearchBinding

    // Fragment
    private lateinit var searchRecyclerFragment: SearchRecyclerFragment

    // Fragment Transaction
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d(TAG, "SearchFragment - onCreate() called")

        binding = FragmentSearchBinding.inflate(layoutInflater)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Log.d(TAG,"LibraryFragment - onAttach() called")
    }

    // 화면 보여주기위해 xml과 연결
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    // 작동하는 곳
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgSearchSearch.setOnClickListener {
            Toast.makeText(context, "검색합니다", Toast.LENGTH_SHORT).show()
            searchRecyclerFragment = SearchRecyclerFragment.newInstance()
            changeFragment(searchRecyclerFragment).commit()
        }

    }

    fun changeFragment(fragment: Fragment): FragmentTransaction {
        transaction = fragmentManager!!.beginTransaction().add(R.id.fl_container, fragment)
        // 뒤로 가기 가능하게
        transaction.addToBackStack(null)
        return transaction
    }

}