package com.example.listview_daangn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.listview_daangn.databinding.ActivityAddBinding


class addActivity : AppCompatActivity() {

    val TAG: String = "로그"
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        // 닫기 누르면 전 화면으로
        binding.btnCloseAdd.setOnClickListener {
            finish()
        }

        //가격 입력할 때 1000원 단위로 , 나오게
        binding.etPriceAdd.addTextChangedListener(watcher(binding.etPriceAdd))


        // 완료 버튼 누르면 데이터 저장
        binding.btnFinishAdd.setOnClickListener {
            var intent = Intent()

            var title = binding.etTitleAdd.text.toString()
            var place = binding.etPlaceAdd.text.toString()
            var price = binding.etPriceAdd.text.toString()
            var content = binding.etContentAdd.text.toString()

            var temp :MyModel

            if(price.equals("") && content.equals("")){
                temp = MyModel(R.drawable.item_add,title,place)
            }
            else if(price.equals("")&& !content.equals("")){
                temp = MyModel(R.drawable.item_add,title,place,content = content)
            }
            else if(!price.equals("")&& content.equals("")){
                temp = MyModel(R.drawable.item_add,title,place,price)
            }
            else{
                temp = MyModel(R.drawable.item_add,title,place,price+"원",content)
            }
            intent.putExtra("myModel",temp)
            setResult(RESULT_OK,intent)
            finish()
        }





    }

}