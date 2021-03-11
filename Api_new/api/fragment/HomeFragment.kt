package com.example.api.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.api.MyContext
import com.example.api.News
import com.example.api.R
import com.example.api.databinding.ActivityMainBinding
import com.example.api.databinding.FragmentHomeBinding
import com.example.api.listview.MyListViewAdapter
import com.example.api.retrofit.INaverAPI
import com.example.api.retrofit.ResultGetSearchNews
import com.example.api.retrofit.RetrofitClient
import com.example.api.utils.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class HomeFragment() : Fragment(){

    companion object{
        const val TAG: String = "로그"

        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }

    val TAG: String = "로그"

    private lateinit var binding : FragmentHomeBinding

    val retrofit: Retrofit? = RetrofitClient().getClient(API.BASE_URL)
    val myAPI : INaverAPI = retrofit!!.create(INaverAPI::class.java)
    var callGetSearchNews = myAPI.getSearchNews(API.CLIENT_ID, API.CLIENT_SECRET,"테스트",display = 50)

    // 리스트 뷰
    lateinit var newsList:ArrayList<News>
    // 어댑터
    lateinit var listAdapter: MyListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d(TAG,"HomeFragment - onCreate() called")


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Log.d(TAG,"HomeFragment - onAttach() called")
    }

    // 화면 보여주기위해 xml과 연결
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Log.d(TAG,"HomeFragment - onCreateView() called")
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_home,container,false)


        newsList = ArrayList<News>()

        binding= FragmentHomeBinding.inflate(layoutInflater)

        // enqueue로 백그라운드에서 스레드 요청, 현재 스레드에서 콜백 처리리
        callGetSearchNews.enqueue(object : Callback<ResultGetSearchNews> {
            override fun onResponse(
                call: Call<ResultGetSearchNews>,
                response: Response<ResultGetSearchNews>
            ) {
                Log.d(TAG,"HomeFragment - onResponse() called - ${response.body()}")
                response.body()!!.items.forEach {
                    val item = News(it.title,it.description,it.link)
                    newsList.add(item)
                    //Log.d(TAG,"MainActivity - onResponse() called - ${item}")
                }
                Log.d(TAG,"${newsList.size}")
                connectList()
            }

            override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                Log.d(TAG,"HomeFragment - onFailure() called")
            }

        })

        // 클릭시 링크로 이동
        binding.listviewMain.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.d(TAG,"HomeFragment - onItemClickListener ")

                //리스트뷰 내용 가져오기
                var index = listAdapter.getItemId(position).toInt()
                var link = newsList[index].link

                val intent = Intent()
                intent.setAction(Intent.ACTION_VIEW)
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.setData(Uri.parse(link.toString()))
                startActivity(intent)
            }


        return view
    }


    fun connectList(){
        // 리스트뷰 만들기
        listAdapter= MyListViewAdapter(context!!,newsList)
        binding.listviewMain.adapter = listAdapter
        Log.d(TAG, "HomeFragment - connectList() called - 총개수 : ${listAdapter.count}")

    }
}