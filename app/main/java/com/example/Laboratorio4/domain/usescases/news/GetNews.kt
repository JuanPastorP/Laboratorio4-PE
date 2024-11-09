package com.example.Laboratorio4.domain.usescases.news

import androidx.paging.PagingData
import com.example.Laboratorio4.domain.model.Article
import com.example.Laboratorio4.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke( sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}