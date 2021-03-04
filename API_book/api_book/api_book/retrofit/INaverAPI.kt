package com.example.api_book.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface INaverAPI {
    @GET("v1/search/book.json")
    fun getSearch(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,  // 검색어
        @Query("sort") sort: String?=null,    // 정렬 옵션
        @Query("d_titl") d_titl: String?=null,    // 책 제목
        @Query("d_auth") d_auth: String?=null,    // 저자
        @Query("d_publ") d_publ: String?=null,    // 출판사
        @Query("d_count") d_count: String?=null,  // 목차
        @Query("d_catg") d_catg: String?=null,   // 카테고리
    ): Call<ResultGetSearch>
}