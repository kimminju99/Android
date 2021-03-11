package com.example.api_book.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api_book.MyContext
import com.example.api_book.R
import com.example.api_book.databinding.FragmentSearchRecyclerBinding
import com.example.api_book.recyclerview.Book
import com.example.api_book.recyclerview.RecyclerViewAdapter
import com.example.api_book.retrofit.RetrofitManager
import com.example.api_book.utils.RESPONSE_STATE

class SearchRecyclerFragment : Fragment(){

    companion object{
        const val TAG: String = "로그"

        fun newInstance():SearchRecyclerFragment{
            return SearchRecyclerFragment()
        }
    }

    // 뷰 바인딩
    private lateinit var binding: FragmentSearchRecyclerBinding


    // 데이터
    private var bookList = ArrayList<Book>()

    // 어댑터
    private var bookAdapter = RecyclerViewAdapter()

    // Fragment Transaction
    private lateinit var transaction : FragmentTransaction



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"SearchRecyclerFragment - onCreate() called")

        binding = FragmentSearchRecyclerBinding.inflate(layoutInflater)

        transaction = fragmentManager!!.beginTransaction()

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

        MyContext.setContext(context!!)

        binding.imgSearchSearchRecycler.setOnClickListener {
            Toast.makeText(context, "검색", Toast.LENGTH_SHORT).show()
            //Log.d(TAG, "SearchRecyclerFragment - onViewCreated() - search btn clicked")
        }

        //edit text enter 누르면 검색결과 보여줌
        binding.etSearchSearchRecycler.setOnKeyListener { v, keyCode, event ->
            when (keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    // 새 fragment로 -> 뒤로가기 가능하게
                    //addFragment(newInstance()).commit()
                    callSearchView()
                    hideKeyboard()
                }
            }
            true
        }

        // 검색어 입력했을 때 변화들
        changeEditText()


        //뒤로가기
        binding.topAppBar.setNavigationOnClickListener {
            Log.d(TAG,"SearchRecyclerFragment - setNavigationOnClickListener")
           transaction.remove(this)
        }

        // recycler View
        binding.recyclerViewSearchRecycler.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerViewSearchRecycler.adapter = bookAdapter

    }

    


    fun callSearchView() {
        Toast.makeText(context, "${binding.etSearchSearchRecycler.text}를 검색합니다", Toast.LENGTH_SHORT).show()
        RetrofitManager.instance.search(
            binding.etSearchSearchRecycler.text.toString(),
            completion = { responseState, responseDataList, totalBook ->
                when (responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api 호출 성공")
                        //Log.d(TAG, "api 호출 성공 : ${totalBook} : ${responseDataList}")
                        bookList = responseDataList!!
                        binding.cardViewResultSearchRecycler.visibility = View.VISIBLE
                        binding.countResultSearchRecycler.text = totalBook.toString()
                        connectRecyclerView()
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(MyContext.context, "api 호출 에러입니다", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api 호출 실패 : ${responseDataList}")
                    }
                    RESPONSE_STATE.NO_CONTENT -> {
                        Toast.makeText(MyContext.context, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    fun connectRecyclerView() {
        // recyclerView 어댑터 연결 및 보여주기
        //Log.d(TAG, "SearchRecyclerFragment - bookList - ${bookList}")
        bookAdapter.submitList(bookList)
        bookAdapter.notifyDataSetChanged()
    }

    fun hideKeyboard() {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    fun changeEditText(){
        //검색 글자 입력시 x 버튼 나오게
        binding.etSearchSearchRecycler.doOnTextChanged { text, start, before, count ->
            if (binding.etSearchSearchRecycler.toString().count() > 0) {
                binding.btnCancelSearchRecycler.visibility = View.VISIBLE
            }
            else{
                binding.btnCancelSearchRecycler.visibility = View.INVISIBLE
            }
        }

        // x 누르면 글자 사라지게
        binding.btnCancelSearchRecycler.setOnClickListener {
            binding.etSearchSearchRecycler.setText(null)
            binding.btnCancelSearchRecycler.visibility = View.INVISIBLE
            binding.cardViewResultSearchRecycler.visibility = View.INVISIBLE
            //bookList.clear()
            //bookAdapter.notifyDataSetChanged()

            // 뒤로가기 가능케 하기위해 계속 fragment add 해주기
            addFragment(SearchRecyclerFragment()).commit()

        }
    }

    fun addFragment(fragment: Fragment): FragmentTransaction {
        Log.d(TAG,"SearchRecyclerFragment - addFragment() called")

        // 뒤로 가기 가능하게
        //transaction.addToBackStack(null)
        return transaction.add(R.id.fl_container,fragment)
    }

}