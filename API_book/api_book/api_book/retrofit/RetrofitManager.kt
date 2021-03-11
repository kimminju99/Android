package com.example.api_book.retrofit

import android.text.Html
import android.util.Log
import com.example.api_book.recyclerview.Book
import com.example.api_book.utils.API.BASE_URL
import com.example.api_book.utils.API.CLIENT_ID
import com.example.api_book.utils.API.CLIENT_SECRET
import com.example.api_book.utils.Constant.TAG
import com.example.api_book.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RetrofitManager {

    // 싱글턴 적용
    companion object{
        val instance = RetrofitManager()
    }

    // retrofit 콜 만들기
    private val retrofit: Retrofit? = RetrofitClient().getClient(BASE_URL)
    private val myAPI :INaverAPI  = retrofit!!.create(INaverAPI::class.java)

    fun search(searchTerm:String, completion:(RESPONSE_STATE,ArrayList<Book>?,Int)->Unit){
        val call = myAPI.getSearch(CLIENT_ID, CLIENT_SECRET,searchTerm,display = 18)

        call.enqueue(object :Callback<ResultGetSearch>{
            override fun onResponse(
                call: Call<ResultGetSearch>,
                response: Response<ResultGetSearch>
            ) {
                Log.d(TAG,"RetrofitManager - onResponse() called")
                when(response.code()){
                    200 ->{
                        response.body()?.let{
                            var parsedBookDataList = ArrayList<Book>()

                            // 데이터 없으면 no_content 보내기
                            if (it.total == 0){
                                completion(RESPONSE_STATE.NO_CONTENT, null,0)
                            }
                            // 데이터 있으면 받아오기
                            else{
                                // item 하나씩 꺼내서 BookList에 넣기
                                it.items.forEach { resultItem->
                                    val BookItem = Book(
                                        title = stripHtml(resultItem.title),
                                        image = resultItem.image,
                                        author = stripHtml(resultItem.author)
                                    )
                                    parsedBookDataList.add(BookItem)
                                }
                                Log.d(TAG,"RetrofitManager - ${parsedBookDataList}")
                                // main_activity로 보내주기
                                completion(RESPONSE_STATE.OKAY,parsedBookDataList, it.total)
                            }
                        }


                    }
                }
            }

            override fun onFailure(call: Call<ResultGetSearch>, t: Throwable) {
                Log.d(TAG,"RetrofitManager - onFailure() called - t:$t")
                completion(RESPONSE_STATE.FAIL,null,0)
            }

        })

    }

    // html Tag 문자열에서 String 값만 빼오기
    fun stripHtml(html:String): String {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
    }

}