package com.example.Laboratorio4.presentation.bookmark

import com.example.Laboratorio4.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
