package com.example.api.retrofit

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글턴
class RetrofitClient {
    val TAG: String = "로그"
    // retrofit client 선언
    private var retrofitClient : Retrofit? = null

    // retrofit client 가져오기
    fun getClient(baseUrl:String): Retrofit? {
        Log.d(TAG,"RetrofitClient - getClient() called")
        if (retrofitClient == null){
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }
}