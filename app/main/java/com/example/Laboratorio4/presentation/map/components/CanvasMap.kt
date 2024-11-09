package com.example.Laboratorio4.presentation.map.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.Laboratorio4.presentation.map.BeaconData

val TAG = "MAP"

@Composable
fun CanvasMap(onTouchPaint: (String) -> Unit, result: List<BeaconData>, ubi: Pair<Float, Float>) {
    var _point by remember { mutableStateOf(Pair(0f, 0f)) }

    var maxCanvasW = 0f.dp
    var maxCanvasH = 0f.dp
    var mToDp = 0f

    //metros
    var maxRoomWidth = 7f
    var maxRoomHeight = 7f

    var maxCanvasWPx = 0f
    var maxCanvasHPx = 0f

    //metros
    var distancesArr = arrayOf(71.49, 79.48, 44.55)

    Column {
        BoxWithConstraints(modifier = Modifier
            .padding(10.dp)) {
            maxCanvasW = maxWidth
            mToDp = maxCanvasW.value / maxRoomWidth
            maxCanvasH = (maxRoomHeight*mToDp).dp

            Canvas(
                modifier = Modifier
                    .size(maxCanvasW, maxCanvasH)
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            Log.d(TAG, "Se toca la pantalla en ${offset}")
                        }
                    }){
                maxCanvasWPx = maxCanvasW.toPx()
                maxCanvasHPx = maxCanvasH.toPx()

                /*
                var path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(maxCanvasW.toPx(), 0f)
                    lineTo(maxCanvasW.toPx(), maxCanvasH.toPx())
                    lineTo(0f, maxCanvasH.toPx())
                    lineTo(0f, 0f)
                }

                drawPath(
                    path = path,
                    color = Color.LightGray,
                    style = Stroke(width = 5.dp.toPx())
                )

                path = Path().apply {
                    moveTo(0f, maxCanvasH.toPx()*.5f)
                    lineTo(0f, maxCanvasH.toPx()*(.5f-.15f))
                    close()
                }

                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 5.dp.toPx())
                )
                */
                _point?.let { (x, y) ->
                    _point = convertPoint(ubi, mToDp)
                    Log.d(TAG, "UBI (dp): $_point")
                    drawPoint(x.dp.toPx(), (maxCanvasH - y.dp).toPx(), Color.Red)
                }

                // Mapa anterior
                var path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    moveTo(size.width, 50f)
                    lineTo(size.width, size.height)
                    lineTo((size.width*79/100), size.height)
                    moveTo((size.width*79/100)-50, size.height)
                    lineTo(size.width*40/100,size.height)
                    moveTo((size.width*40/100)-50, size.height)
                    lineTo(0f,size.height)
                    lineTo(0f, 0f)
                    moveTo(size.width*55/100,0f)
                    lineTo(size.width*55/100,size.height*29/100)
                    lineTo(size.width-50,size.height*29/100)
                    moveTo(size.width*55/100,size.height*29/100)
                    lineTo(size.width*515/1000,size.height*29/100)

                    moveTo(size.width*48/100,0f)
                    lineTo(size.width*48/100,size.height*29/100-50)
                    lineTo(size.width*515/1000,size.height*29/100-50)
                    lineTo(size.width*515/1000,size.height*29/100-25)
                    moveTo(size.width*48/100,size.height*29/100-50)
                    lineTo(size.width*41/100,size.height*29/100-50)
                    lineTo(size.width*41/100,size.height*29/100)
                    lineTo(size.width*41/100+50,size.height*29/100)
                    moveTo(size.width*41/100,size.height*29/100)
                    lineTo(size.width*41/100-25,size.height*29/100)

                    moveTo(size.width*41/100-75,size.height*29/100)
                    lineTo(0f,size.height*29/100)

                    moveTo(size.width-50, size.height*71/100)
                    lineTo(size.width-100, size.height*71/100)
                    lineTo(size.width-100, size.height*805/1000)
                    moveTo(size.width-100, size.height*805/1000 + 50)
                    lineTo(size.width-100, size.height)
                    moveTo(size.width-100, size.height-100)
                    lineTo(size.width*40/100, size.height-100)
                }

                drawPath(
                    path = path,
                    color = Color.LightGray,
                    style = Stroke(width = 5.dp.toPx())
                )

                path = Path().apply {
                    moveTo(0f, size.height*.5f)
                    lineTo(0f, size.height*(.5f-.15f))

                    close()
                }

                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 5.dp.toPx())
                )
                /*
                point?.let { (x, y) ->
                    drawPoint(x, y, Color.Red)
                }
                */
            }
            picture("3e5dc079-4b55-493d-9943-6d08625c7fbe", maxWidth*.1f, maxHeight*.1f, maxWidth, maxHeight, onTouchPaint)
            picture("10e9a825-8a96-4a88-a3d1-102c4be24db7", maxWidth*.3f, maxHeight*.1f, maxWidth, maxHeight, onTouchPaint)
            picture("deb6091a-a77b-4b05-af44-3d733f3f34d9", maxWidth*.6f, maxHeight*.1f, maxWidth, maxHeight, onTouchPaint)
            picture("c2e7ec03-7594-4885-8325-9a329ceb9e81", maxWidth*.8f, maxHeight*.1f, maxWidth, maxHeight, onTouchPaint)
        }

        Button(onClick = { _point = convertPoint(ubi, mToDp) }) {
            Text("Mi ubicación")
        }



        val beaconInfo = result.joinToString(separator = "\n") { beacon ->
            "UUID: ${beacon.uuid}\nDistance: ${beacon.distance}\nMinor: ${beacon.minor}"
        }

        // Muestra la información de los Beacons en un Text composable
        Text(text = beaconInfo)
    }

}

fun DrawScope.drawPoint(x: Float, y: Float, color: Color) {
    drawCircle(
        color = color,
        center = Offset(x, y),
        radius = 5.dp.toPx()
    )
}

@Composable
fun picture(ID: String, x: Dp, y: Dp, w: Dp, h: Dp, onTouchPaint: (String) -> Unit) {
    Canvas(
        modifier = Modifier
            .size(w*.1f)
            .offset(x, y)
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    Log.d(TAG, "Se toca la pintura ${ID} en ${offset}")
                    onTouchPaint(ID)
                }
            }
    ){
        drawRect(
            color = Color.Red
        )
    }
}

fun convertPoint(p: Pair<Float, Float>, c: Float): Pair<Float, Float>{
    var res = Pair(p.first*c, p.second*c)
    Log.d(TAG, "$p")
    return res
}
