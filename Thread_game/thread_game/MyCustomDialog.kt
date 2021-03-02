package com.example.thread_game

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.thread_game.databinding.CustomDialogBinding

class MyCustomDialog(context: Context) : AlertDialog(context){

    private lateinit var binding : CustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 배경 투명
        //window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}