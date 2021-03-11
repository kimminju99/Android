package com.example.api.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api.MyContext.Companion.context
import com.example.api.News
import com.example.api.databinding.ActivityMainBinding
import com.example.api.databinding.ActivitySearchBinding
import com.example.api.listview.MyListViewAdapter
import com.example.api.retrofit.INaverAPI
import com.example.api.retrofit.ResultGetSearchNews
import com.example.api.retrofit.RetrofitClient
import com.example.api.utils.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class SearchActivity : AppCompatActivity() {
    val TAG: String = "로그"

    // 뷰 바인딩
    private lateinit var binding : ActivitySearchBinding

    val retrofit: Retrofit? = RetrofitClient().getClient(API.BASE_URL)
    val myAPI : INaverAPI = retrofit!!.create(INaverAPI::class.java)
    private lateinit var callGetSearchNews :Call<ResultGetSearchNews>

    // 리스트 뷰
    lateinit var newsList:ArrayList<News>
    // 어댑터
    lateinit var listAdapter: MyListViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsList = ArrayList<News>()

        //edit text enter 누르면 검색결과 보여줌
        binding.etSearchSearch.setOnKeyListener { v, keyCode, event ->
            when (keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
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
            finish()
        }



        // 클릭시 링크로 이동
        binding.ListViewMain.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.d(TAG,"MainActivity - onItemClickListener ")

                //리스트뷰 내용 가져오기
                var index = listAdapter.getItemId(position).toInt()
                var link = newsList[index].link

                val intent = Intent()
                intent.setAction(Intent.ACTION_VIEW)
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.setData(Uri.parse(link.toString()))
                startActivity(intent)
            }



    }

    fun callSearchView(){
        binding.cardViewResultMain.visibility= View.VISIBLE

        callGetSearchNews= myAPI.getSearchNews(API.CLIENT_ID, API.CLIENT_SECRET,
            binding.etSearchSearch.text.toString(),display = 50)

        // enqueue로 백그라운드에서 스레드 요청, 현재 스레드에서 콜백 처리리
        callGetSearchNews.enqueue(object : Callback<ResultGetSearchNews> {
            override fun onResponse(
                call: Call<ResultGetSearchNews>,
                response: Response<ResultGetSearchNews>
            ) {
                Log.d(TAG,"MainActivity - onResponse() called - ${response.body()}")
                binding.countResultSearch.text = response.body()!!.total.toString()
                response.body()!!.items.forEach {
                    val item = News(it.title,it.description,it.link)
                    newsList.add(item)
                    //Log.d(TAG,"MainActivity - onResponse() called - ${item}")
                }
                Log.d(TAG,"${newsList.size}")
                connectList()
            }

            override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                Log.d(TAG,"MainActivity - onFailure() called")
            }

        })
    }

    fun connectList(){
        // 리스트뷰 만들기
        listAdapter= MyListViewAdapter(this,newsList)
        binding.ListViewMain.adapter = listAdapter
        listAdapter.notifyDataSetChanged()
        Log.d(TAG, "MainActivity - connectList() called - 총개수 : ${listAdapter.count}")

    }

    fun hideKeyboard(){
        val inputManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            this.currentFocus!!.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun changeEditText(){
        //검색 글자 입력시 x 버튼 나오게
        binding.etSearchSearch.doOnTextChanged { text, start, before, count ->
            if (binding.etSearchSearch.toString().count() > 0) {
                binding.btnCancelMain.visibility = View.VISIBLE
            }
            else{
                binding.btnCancelMain.visibility = View.INVISIBLE
            }
        }

        // x 누르면 글자 사라지게
        binding.btnCancelMain.setOnClickListener {
            binding.etSearchSearch.setText(null)
            binding.btnCancelMain.visibility = View.INVISIBLE
            newsList.clear()
            listAdapter.notifyDataSetChanged()
        }

    }

    // todo 0. 재검색시 갱신, text 글자 지워지게
    // todo 1. fragment에서 구현
    // todo 2. 데이터 2차 가공 태그 안보이게
    // todo 3. recyclerview 
    // todo 4. 로그인 api

}