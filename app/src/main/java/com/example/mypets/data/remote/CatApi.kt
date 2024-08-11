package com.example.mypets.data.remote

import com.example.mypets.BuildConfig
import com.example.mypets.data.model.CatModel
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.CAT_API_KEY
interface CatApi {
    @GET("v1/images/search?api_key=$API_KEY")
    suspend fun getImages(
        @Query("limit") num: Int = 1
    ): CatModel
}
