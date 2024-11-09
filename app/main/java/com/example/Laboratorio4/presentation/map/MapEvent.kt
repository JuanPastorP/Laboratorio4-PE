package com.example.Laboratorio4.presentation.map

import com.example.Laboratorio4.domain.model.Article


sealed class MapEvent {
    data class RedirectoToDetails(
        val qrResult: String,
        val navigateToDetails: (Article) -> Unit
    ): MapEvent()
}