package com.example.template_practice.src.main.home.models

import com.google.gson.annotations.SerializedName

data class UserSearchResult (
    @SerializedName("userId") val userId: Int,
    @SerializedName("email") val email : String
    )