package com.example.template_practice.src.main.home

import com.example.template_practice.src.main.home.models.PostSignUpRequest
import com.example.template_practice.src.main.home.models.SignUpResponse
import com.example.template_practice.src.main.home.models.UserResponse
import com.example.template_practice.src.main.home.models.UserSearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeRetrofitInterface {
    @GET("/users")
    fun getUsers() : Call<UserResponse>

    @POST("/users")
    fun postSignUp(@Body params: PostSignUpRequest):Call<SignUpResponse>

    @GET("/users")
    fun getUserSearch(@Query("word") word:String):Call<UserSearchResponse>

}