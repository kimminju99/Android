package com.example.listview_daangn

import android.app.DownloadManager
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.listview_daangn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    // 뷰 바인딩
    private lateinit var binding: ActivityMainBinding

    // 리스트 추가를 위한 변수
    var addition: Int = 1

    // 데이터 담을 list
    var modelList = ArrayList<MyModel>()

    // 어댑터
    lateinit var listAdapter: MyListViewAdapter

    // 검색기능 구현을 위한 searchView
    lateinit var searchView:SearchView


    // 뷰가 화면에 그려질때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // modellist 내용 listview에 넣기
        addDatalist()

        // Adapter 인스턴스 생성
        listAdapter = MyListViewAdapter(this, modelList)
        binding.listviewMain.adapter = listAdapter
        Log.d(TAG, "MainActivity - onCreate() called - 총개수 : ${listAdapter.count}")


        


        // 아이템 조회
        // list item 클릭하면 정보 받아서 second activity로
        binding.listviewMain.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, SecondActivity::class.java)
                Log.d(TAG, "MainActivity - onItemClickListener")

                //리스트뷰 내용 가져오기
                var index = listAdapter.getItemId(position).toInt()

                intent.putExtra("content_image", modelList[index].image)
                intent.putExtra("content_title", modelList[index].title)
                intent.putExtra("content_place", modelList[index].place)
                intent.putExtra("content_price", modelList[index].price)
                intent.putExtra("content_content", modelList[index].content)

                intent.putExtra("second", modelList[index])
                startActivity(intent)
            }


        // 아이템 삭제
        //아이템 길게 누르면 "삭제" dialog -> listview 삭제
        binding.listviewMain.setOnItemLongClickListener { parent, view, position, id ->
            //Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show()
            Log.d("MainActivity", "test")

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("삭제")
            dialog.setMessage("삭제하시겠습니까?")
            dialog.setNegativeButton("취소",
                DialogInterface.OnClickListener { dialog, which -> })
            dialog.setPositiveButton("삭제") { dialog, which ->
                // 현재 선택된 아이템 position 선택
                Toast.makeText(this, "" + position, Toast.LENGTH_SHORT)
                modelList.removeAt(position)
                // listview 갱신
                listAdapter.notifyDataSetChanged()
                Log.d(
                    TAG,
                    "MainActivity - 삭제 위치 : ${
                        listAdapter.getItemId(position).toInt()
                    } - 총개수 : ${listAdapter.count}"
                )
            }
            dialog.show()

            true
        }


        // 아이템 추가
        //추가 버튼 floating button 누르면 addActivity로 -> 리스트 추가
        binding.floatingActionButtonMain.setOnClickListener { view ->
            val intent = Intent(this, addActivity::class.java)
            startActivityForResult(intent, addition)
            listAdapter.notifyDataSetChanged()
        }

        // 액티비티에서 어떤 액션 바를 사용할지 설정
        var appbar = supportActionBar
    }



    // 앱 바 사용되도록 -> OptionsMenu : 액티비티 기본 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        Log.d(TAG, "MainActivity - onCreateOptionsMenu() called")
        // inflate menu xml
        menuInflater.inflate(R.menu.top_app_bar_home, menu)

        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        // 검색 버튼 클릭시 힌트 추가
        searchView.queryHint="검색어를 입력하세요".toString()

        // SearchView 위젯 구성
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.action_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }


        return true
    }




    // startActivityForResult로 결과 받아오려고
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Adapter 인스턴스 생성
        binding.listviewMain.adapter = listAdapter

        if (requestCode == addition) {
            if (resultCode == RESULT_OK) {
                var item = data!!.getParcelableExtra<MyModel>("myModel")
                modelList.add(0, item!!)
                listAdapter.notifyDataSetChanged()
                Log.d(TAG, "MainActivity - onActivityResult() - ${item}")
                Log.d(TAG, "MainActivity - onActivityResult 추가 - 총개수 : ${listAdapter.count}")
            }
        }
    }


    fun addDatalist() {
        for (i in 1..20) {
            modelList.add(
                MyModel(
                    R.drawable.item,
                    "뉴발란스 키즈 170",
                    "영등포구 문래동5가",
                    "15,000원",
                    "보시다시피 엄청 깨끗합니다.\n\n작아져서 판매해요^^"
                )
            )
            modelList.add(
                MyModel(
                    R.drawable.item1,
                    "제닉스 카카오 게이밍 의자",
                    "신정7동",
                    "90,000원",
                    "별다른 하자 없습니다.\n저희 집 근처 오셔서 가져가셔야 합니다."
                )
            )
            modelList.add(
                MyModel(
                    R.drawable.item2,
                    "IFNE 이프네 트렌치코트",
                    "구로구 구로동",
                    "40,000원",
                    "멋진 커리어 우먼스러운~~ \n어디든 위에 입기 좋을 거예요~~"
                )
            )
            modelList.add(
                MyModel(
                    R.drawable.item3,
                    "총균쇠. 중고 서적",
                    "양천구 목1동",
                    "25,000원",
                    "완전 새 책. 한번도 안펴봄\n\n직거래 지하철역"
                )
            )
            modelList.add(MyModel(R.drawable.item4, "라인인형", "강서구 화곡동", "20,000원", "다이노 브라운 판매합니다"))
            modelList.add(
                MyModel(
                    R.drawable.ipad,
                    "아이패드 6세대(거의 새거)",
                    "화곡동",
                    "300,000원",
                    "기스 흠집 아예 없어요\n사용 거의 안했어요. 새거라고 보시면 됩니다.\n화질 완전 좋아요\n\n직거래, 택배 가능합니다!"
                )
            )
        }
        for (i in 1..20) {
            val myModel = MyModel(
                image = R.drawable.ic_launcher_background,
                title = "아이템 $i",
                place = "양천구 신정${i}동",
                "${i * 10}만원"
            )
            this.modelList.add(myModel)
        }
    }



}







