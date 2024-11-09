package com.example.Laboratorio4.presentation.search

import androidx.paging.PagingData
import com.example.Laboratorio4.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)