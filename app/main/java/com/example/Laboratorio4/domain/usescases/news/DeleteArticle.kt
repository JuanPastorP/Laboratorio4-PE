package com.example.Laboratorio4.domain.usescases.news

import com.example.Laboratorio4.data.local.NewsDao
import com.example.Laboratorio4.domain.model.Article
import com.example.Laboratorio4.domain.repository.NewsRepository

class DeleteArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}