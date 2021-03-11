package com.example.api.retrofit

data class ResultGetSearchNews(
    var lastBuildDate: String = "",
    var total: Int =0,
    var start: Int =0,
    var display: Int = 0,
    var items:List<Items>
)

data class Items(
    var title:String="",
    var originallink:String="",
    var link:String="",
    var description:String="",
)