package com.example.Laboratorio4.presentation.map

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.Laboratorio4.domain.model.Article
import com.example.Laboratorio4.presentation.Dimens
import com.example.Laboratorio4.presentation.common.ScanCamara
import com.example.Laboratorio4.presentation.map.components.CanvasMap

@Composable
fun MapScreen(
    result: List<BeaconData>,
    ubi: Pair<Float, Float>,
    event: (MapEvent) -> Unit,
    navigateToDetails: (Article) -> Unit) {
    ScanCamara(
        onResult = { event(MapEvent.RedirectoToDetails(it, navigateToDetails))}
    )
    Box(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        CanvasMap(
            onTouchPaint = { event(MapEvent.RedirectoToDetails(it, navigateToDetails))},
            result = result,
            ubi = ubi,
        )

    }
}