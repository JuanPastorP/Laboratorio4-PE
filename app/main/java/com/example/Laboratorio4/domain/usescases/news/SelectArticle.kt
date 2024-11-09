package com.example.Laboratorio4.domain.usescases.news

import com.example.Laboratorio4.data.local.NewsDao
import com.example.Laboratorio4.domain.model.Article
import com.example.Laboratorio4.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article?{
        return newsRepository.getArticle(url)
    }
}