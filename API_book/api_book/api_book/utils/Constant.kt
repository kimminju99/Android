package com.example.api_book.utils

object Constant {
    const val TAG: String = "로그"
}

object API{
    const val BASE_URL : String ="https://openapi.naver.com/"
    const val CLIENT_ID = "09lYSNMYXZiezqcQ49NN"
    const val CLIENT_SECRET ="fonOjIGcTJ"
}

enum class RESPONSE_STATE{
    OKAY,
    FAIL,
    NO_CONTENT
}