package com.example.api

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import com.example.api.databinding.ActivityMainBinding
import com.example.api.listview.MyListViewAdapter
import com.example.api.retrofit.INaverAPI
import com.example.api.retrofit.ResultGetSearchNews
import com.example.api.retrofit.RetrofitClient
import com.example.api.utils.API.BASE_URL
import com.example.api.utils.API.CLIENT_ID
import com.example.api.utils.API.CLIENT_SECRET
import retrofit2.*

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"
    
    private lateinit var binding : ActivityMainBinding

    val retrofit: Retrofit? = RetrofitClient().getClient(BASE_URL)
    val myAPI :INaverAPI  = retrofit!!.create(INaverAPI::class.java)
    var callGetSearchNews = myAPI.getSearchNews(CLIENT_ID,CLIENT_SECRET,"속보",display = 50)

    // 리스트 뷰
    lateinit var newsList:ArrayList<News>
    // 어댑터
    lateinit var listAdapter: MyListViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsList = ArrayList<News>()

        // enqueue로 백그라운드에서 스레드 요청, 현재 스레드에서 콜백 처리리
       callGetSearchNews.enqueue(object : Callback<ResultGetSearchNews>{
            override fun onResponse(
                call: Call<ResultGetSearchNews>,
                response: Response<ResultGetSearchNews>
            ) {
                Log.d(TAG,"MainActivity - onResponse() called - ${response.body()}")
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

        // 클릭시 링크로 이동
        binding.listviewMain.onItemClickListener =
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

    fun connectList(){
        // 리스트뷰 만들기
        listAdapter= MyListViewAdapter(this,newsList)
        binding.listviewMain.adapter = listAdapter
        Log.d(TAG, "MainActivity - connectList() called - 총개수 : ${listAdapter.count}")

    }


}