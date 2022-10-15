package com.anonymous.trivia.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

@Composable
fun DashedLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    pathEffect: PathEffect
) {
    Canvas(modifier = Modifier
        .fillMaxWidth().padding(10.dp)
        .then(modifier), onDraw = {
        drawLine(
            color = color,
            Offset(0f, 0f),
            Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    })
}