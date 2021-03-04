package com.example.api_book

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api_book.MyContext.Companion.context
import com.example.api_book.databinding.ActivityMainBinding
import com.example.api_book.recyclerview.Book
import com.example.api_book.recyclerview.RecyclerViewAdapter
import com.example.api_book.retrofit.RetrofitManager
import com.example.api_book.utils.RESPONSE_STATE

class MainActivity : AppCompatActivity() {
    val TAG: String = "로그"

    // 데이터
    private var bookList = ArrayList<Book>()

    // 뷰 바인딩
    private lateinit var binding: ActivityMainBinding

    // 어댑터
    private var bookAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // context 가져오기
        MyContext.Companion.setContext(this)


        //edit text enter 누르면 검색결과 보여줌
        binding.etSearchMain.setOnKeyListener { v, keyCode, event ->
            when (keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    callSearchView()
                    hideKeyboard()
                }
            }
            true
        }


        //검색 글자 입력시 x 버튼 나오게
        binding.etSearchMain.doOnTextChanged { text, start, before, count ->
            if (binding.etSearchMain.toString().count() > 0) {
                binding.btnCancelMain.visibility = View.VISIBLE
            }
            else{
                binding.btnCancelMain.visibility = View.INVISIBLE
            }
        }

        // x 누르면 글자 사라지게
        binding.btnCancelMain.setOnClickListener {
            binding.etSearchMain.setText(null)
        }

        //뒤로가기
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }


    }

    fun callSearchView() {
        Toast.makeText(this, "보여줘 ${binding.etSearchMain.text}", Toast.LENGTH_SHORT).show()
        RetrofitManager.instance.search(
            binding.etSearchMain.text.toString(),
            completion = { responseState, responseDataList ->
                when (responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api 호출 성공 : ${responseDataList}")
                        bookList = responseDataList!!
                        connectRecyclerView()
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "api 호출 에러입니다", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api 호출 실패 : ${responseDataList}")
                    }
                    RESPONSE_STATE.NO_CONTENT -> {
                        Toast.makeText(this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    fun connectRecyclerView() {
        // recyclerView 어댑터 연결 및 보여주기
        Log.d(TAG, "MainActivity - connectRecyclerView() called - ${bookList}")
        bookAdapter.submitList(bookList)
        binding.recyclerViewMain.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerViewMain.adapter = bookAdapter
    }

    fun hideKeyboard(){
        val inputManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            this.currentFocus!!.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}