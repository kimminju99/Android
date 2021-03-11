package com.example.template_practice.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultUser (
    // Gson : Json과 객체 간 직렬화와 역직렬화를 위한 라이브러리
    @SerializedName("userId") val userId:Int,
    @SerializedName("email") val email:String
)