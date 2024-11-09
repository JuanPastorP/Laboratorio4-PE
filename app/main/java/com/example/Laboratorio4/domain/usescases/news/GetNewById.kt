package com.example.Laboratorio4.domain.usescases.news

import com.example.Laboratorio4.data.remote.dto.NewsResponse
import com.example.Laboratorio4.domain.model.ArticleResponse
import com.example.Laboratorio4.domain.repository.NewsRepository

class GetNewById (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(sources: List<String>, id: String): ArticleResponse {
        return newsRepository.getNewsById(sources = sources, id)
    }
}