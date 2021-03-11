package com.example.api_book.retrofit

import android.text.Html

data class ResultGetSearch (
    var total:Int=0,
    var items:List<Items>
)

data class Items(
    var title:String="",
    var image:String ="",
    var author:String="",
)

data class Item_detail(
    var title:String ="",
    var image:String ="",
    var author:String="",
    var publisher:String="",
    var pubdate:Int=0,
    var price:Int=0,
    var description:String="",
)