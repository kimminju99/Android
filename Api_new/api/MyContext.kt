package com.example.api

import android.content.Context

abstract class MyContext {
    companion object{
        internal lateinit var context: Context

        fun setContext(con: Context){
            context = con
        }
    }

}