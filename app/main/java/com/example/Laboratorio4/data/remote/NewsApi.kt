package com.example.Laboratorio4.data.remote

import com.example.projecto_suarez.data.remote.dto.NewsResponse
import com.example.projecto_suarez.domain.model.ArticleResponse
import com.example.projecto_suarez.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("pictures")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("pictures")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("pictures/{id}")
    suspend fun getNewsById(
        @Path("id") id: String ): ArticleResponse

}