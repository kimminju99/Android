package com.example.api_book

import android.app.Application
import android.content.Context

// 전역으로 context 사용하기 위해
class App:Application() {
    init {
        instance=this
    }

    companion object{
        lateinit var instance:App
            private set

        fun applicationContext():Context{
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context = App.applicationContext()
    }


}