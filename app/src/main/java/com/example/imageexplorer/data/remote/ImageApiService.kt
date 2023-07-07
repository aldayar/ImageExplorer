package com.example.imageexplorer.data.remote

import com.example.imageexplorer.data.model.Hit
import com.example.imageexplorer.data.model.ImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {

    @GET("/api/")
    fun getImage(
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = null,
        @Query("category") category: List<String>? = null,
        @Query("key") apiKey: String ="38091514-7cb315d0a956801ba964bf2d9",
    ): Call<ImageResponse<Hit>>

}