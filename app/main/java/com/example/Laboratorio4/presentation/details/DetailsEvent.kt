package com.example.Laboratorio4.presentation.details

import com.example.Laboratorio4.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    object RemoveSideEffect: DetailsEvent()
}