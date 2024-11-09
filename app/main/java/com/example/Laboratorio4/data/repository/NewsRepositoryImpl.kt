package com.example.Laboratorio4.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.Laboratorio4.data.local.NewsDao
import com.example.Laboratorio4.data.remote.NewsApi
import com.example.Laboratorio4.data.remote.NewsPagingSource
import com.example.Laboratorio4.data.remote.SearchNewsPagingSource
import com.example.Laboratorio4.data.remote.dto.NewsResponse
import com.example.Laboratorio4.domain.model.Article
import com.example.Laboratorio4.domain.model.ArticleResponse
import com.example.Laboratorio4.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private  val newsApi: NewsApi,
    private val newsDao: NewsDao

): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString( separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = ""
                )
            }
        ).flow
    }
    override suspend fun getNewsById(sources: List<String>, id: String): ArticleResponse {
        return newsApi.getNewsById(id)
    }
    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticle(url = url)
    }
}