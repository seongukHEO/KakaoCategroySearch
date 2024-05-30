package kr.co.lion.application.usekakaomapapi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAPI {
    @GET("v2/local/search/category.json")
    fun getSearchKeyword(
        @Header("Authorization") key: String,
        @Query("category_group_code") categoryGroupCode: String,
        @Query("x") x: String,
        @Query("y") y: String,
        @Query("radius") radius: Int
    ) : Call<ResultKeyword>
}
