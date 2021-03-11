package com.example.template_practice.src.main.home

import com.example.template_practice.config.ApplicationClass
import com.example.template_practice.src.main.home.models.PostSignUpRequest
import com.example.template_practice.src.main.home.models.SignUpResponse
import com.example.template_practice.src.main.home.models.UserResponse
import com.example.template_practice.src.main.home.models.UserSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeService(val view:HomeFragmentView) {

    fun tryGetUsers(){
        val homeRetrofitInterFace = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterFace.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                view.onGetUserSuccess(response.body() as UserResponse)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.onGetUserFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object :Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryGetUserSearch(word:String){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getUserSearch(word).enqueue(object :Callback<UserSearchResponse>{
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                view.onGetUserSearchSuccess(response.body() as UserSearchResponse)
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                 view.onGetUserSearchFailure(t.message ?:"통신 오류")
            }

        })
    }
}