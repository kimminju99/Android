package com.example.template_practice.src.main.home.models

import com.example.template_practice.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
//        @SerializedName("isSuccess") val isSuccess: Boolean = false,
//        @SerializedName("code") val code: Int = 0,
//        @SerializedName("message") val message: String? = null
    val result: ArrayList<UserSearchResult>
) : BaseResponse()