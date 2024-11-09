package com.example.Laboratorio4.domain.repository

import com.example.Laboratorio4.domain.model.Article
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import com.example.Laboratorio4.data.remote.dto.NewsResponse
import com.example.Laboratorio4.domain.model.ArticleResponse

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
    suspend fun getNewsById(sources: List<String>, id: String): ArticleResponse
    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)
    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?

}