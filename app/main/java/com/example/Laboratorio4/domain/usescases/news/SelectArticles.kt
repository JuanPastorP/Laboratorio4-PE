package com.example.Laboratorio4.domain.usescases.news

import com.example.Laboratorio4.data.local.NewsDao
import com.example.Laboratorio4.domain.model.Article
import com.example.Laboratorio4.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.getArticles()
    }
}