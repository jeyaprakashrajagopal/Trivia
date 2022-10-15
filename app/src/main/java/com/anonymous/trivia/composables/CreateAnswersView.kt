package com.anonymous.trivia

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.anonymous.trivia.model.QuestionItem

@Composable
fun CreateAnswersView(
    data: QuestionItem?,
    selected: MutableState<String>,
    isCorrectAnswer: (String) -> Boolean
) {

    val isSelectedItem: (String) -> Boolean = {
        selected.value == it
    }

    val onChangeState: (String) -> Unit = {
        selected.value = it
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(data?.choices!!) {
            CreateAnswersRow(selected, isSelectedItem, it, onChangeState, isCorrectAnswer)
        }
    }
}

@Composable
fun CreateAnswersRow(
    selected: MutableState<String>,
    isSelectedItem: (String) -> Boolean,
    it: String,
    onChangeState: (String) -> Unit,
    isCorrectAnswer: (String) -> Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .height(45.dp)
            .border(
                width = 4.dp, brush = Brush.linearGradient(listOf(Color.Blue, Color.Blue)),
                shape = RoundedCornerShape(15.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .selectable(
                selected = isSelectedItem(it),
                onClick = { onChangeState(it) },
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            modifier = Modifier.padding(10.dp),
            selected = isSelectedItem(it),
            colors = RadioButtonDefaults.colors(
                selectedColor = if (isCorrectAnswer(it)) Color.Green.copy(alpha = 0.2f) else Color.Red.copy(
                    alpha = 0.2f
                )
            ),
            onClick = null
        )
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Light,
                    color = if (selected.value.isNotBlank() && isCorrectAnswer(it) && isSelectedItem(it)) {
                        Color.Green
                    } else if (selected.value.isNotBlank() && !isCorrectAnswer(it) && isSelectedItem(it)) {
                        Color.Red
                    } else {
                        Color.Black
                    }
                )
            ) {
                append(it)
            }
        })
    }
}