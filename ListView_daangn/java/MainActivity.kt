package com.example.listview_daangn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import com.example.listview_daangn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 뷰 바인딩
    private lateinit var binding:ActivityMainBinding

    val TAG: String = "로그"


    // 데이터 담을 list
    var modelList = ArrayList<MyModel>()

    // 뷰가 화면에 그려질때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"MainActivity - onCreate() called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // modellist 내용 listview에 넣기
        for(i in 1..20) {
            modelList.add(MyModel(R.drawable.item, "뉴발란스 키즈 170", "영등포구 문래동5가", "15,000원"))
            modelList.add(MyModel(R.drawable.item1, "제닉스 카카오 게이밍 의자", "신정7동", "90,000원"))
            modelList.add(MyModel(R.drawable.item2, "IFNE 이프네 트렌치코트", "구로구 구로동", "40,000원"))
            modelList.add(MyModel(R.drawable.item3, "총균쇠. 중고 서적", "양천구 목1동", "25,000원"))
            modelList.add(MyModel(R.drawable.item4, "라인인형", "강서구 화곡동", "20,000원"))
            modelList.add(MyModel(R.drawable.ipad, "아이패드 6세대(거의 새거)", "화곡동", "300,000원"))

        }
        for (i in 1..20){
            val myModel = MyModel(image = R.drawable.ic_launcher_background,title = "아이템 $i",place ="양천구 신정${i}동","${i*10}만원")
            this.modelList.add(myModel)

        }


        // Adapter 인스턴스 생성
        val Adapter = MyListViewAdapter(this,modelList)
        binding.listviewMain.adapter = Adapter


        // list item 클릭하면 정보 받아서 second activity로
        binding.listviewMain.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SecondActivity::class.java )
            Log.d(TAG,"MainActivity - onItemClickListener")

            //리스트뷰 내용 가져옴
            var content_image = Adapter.getItemImage(position)?.toInt()
            var content_title = Adapter.getItemTitle(position).toString()
            var content_place = Adapter.getItemPlace(position).toString()
            var content_price = Adapter.getItemPrice(position).toString()

            intent.putExtra("content_image",content_image)
            intent.putExtra("content_title",content_title)
            intent.putExtra("content_place",content_place)
            intent.putExtra("content_price",content_price)

            startActivity(intent)
        }

    }
}


