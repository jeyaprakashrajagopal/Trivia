package com.anonymous.trivia

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CreateNextButton(
    selected: MutableState<String>,
    onNextClicked: () -> Unit,
    context: Context
) {
    Button(
        onClick = {
            if (selected.value.isNotBlank()) {
                selected.value = ""
                onNextClicked.invoke()
            } else {
                Toast.makeText(
                    context,
                    "Please select an item!",
                    Toast.LENGTH_LONG
                ).show()
            }
        },
        modifier = Modifier
            .wrapContentWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Next", color = Color.White, modifier = Modifier
                .wrapContentWidth()
                .padding(10.dp)
        )
    }
}
