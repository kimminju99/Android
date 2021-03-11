package com.example.template_practice.src.main.home

import com.example.template_practice.src.main.home.models.SignUpResponse
import com.example.template_practice.src.main.home.models.UserResponse
import com.example.template_practice.src.main.home.models.UserSearchResponse

interface HomeFragmentView {

        fun onGetUserSuccess(response: UserResponse)

        fun onGetUserFailure(message:String)

        fun onPostSignUpSuccess(response: SignUpResponse)

        fun onPostSignUpFailure(message: String?)

        fun onGetUserSearchSuccess(response: UserSearchResponse)

        fun onGetUserSearchFailure(message: String?)

}