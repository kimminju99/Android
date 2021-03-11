package com.example.template_practice.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.template_practice.R
import com.example.template_practice.config.BaseFragment
import com.example.template_practice.databinding.FragmentHomeBinding
import com.example.template_practice.src.main.home.models.PostSignUpRequest
import com.example.template_practice.src.main.home.models.SignUpResponse
import com.example.template_practice.src.main.home.models.UserResponse
import com.example.template_practice.src.main.home.models.UserSearchResponse

class HomeFragment:BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {
    val TAG: String = "로그"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeBtnTryGetJwt.setOnClickListener {
            showLoadingDialog(context!!)
            HomeService(this).tryGetUsers()
        }

        binding.homeBtnTryPostHttpMethod.setOnClickListener {
            val email = binding.homeEtId.text.toString()
            val password = binding.homeEtPw.text.toString()
            val postRequest = PostSignUpRequest(email,password,password,"test","010-5592-4249")

            showLoadingDialog(context!!)
            HomeService(this).tryPostSignUp(postRequest)
        }

        binding.homeBtnSearch.setOnClickListener {
            val word = binding.homeEtWord.text.toString()

            showLoadingDialog(context!!)
            HomeService(this).tryGetUserSearch(word)
        }
    }

    override fun onGetUserSuccess(response: UserResponse){
        dismissLoadingDialog()
        for (User in response.result){
            Log.d(TAG,"HomeFragment - ${User.toString()}")
        }
        binding.homeBtnTryGetJwt.text = response.message
        showCustomToast("Get JWT 성공")
    }

    override fun onGetUserFailure(message:String){
        dismissLoadingDialog()
        showCustomToast("오류: ${message}")
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        binding.homeBtnTryPostHttpMethod.text = response.message
        response.message?.let { showCustomToast(it) }
    }

    override fun onPostSignUpFailure(message: String?) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onGetUserSearchSuccess(response: UserSearchResponse) {
        dismissLoadingDialog()
        Log.d("http","HomeFragment - onGetUserSearchSuccess() called - ${response.result.size}")
        for (user in response.result){
            showCustomToast(user.email)
        }
    }

    override fun onGetUserSearchFailure(message: String?) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }


}