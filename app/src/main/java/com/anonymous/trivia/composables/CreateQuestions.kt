package com.anonymous.trivia

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anonymous.trivia.model.QuestionItem
import com.anonymous.trivia.model.TriviaViewModel
import com.anonymous.trivia.widgets.DashedLine

@Composable
fun CreateQuestions(viewModel: TriviaViewModel = hiltViewModel()) {
    val questions = viewModel.questions.collectAsState()
    val data = questions.value.data?.toMutableList()
    val questionNumber = viewModel.questionNo.collectAsState()

    val selected = remember {
        mutableStateOf("")
    }

    if (questions.value.loading == true) {
        CircularProgressBarDisplay(size = 100.dp)
    } else {
        DisplayQuestion(
            questionNumber.value,
            questions.value.data?.size ?: 0,
            data?.get(questionNumber.value),
            selected
        ) {
            viewModel.incrementQuestionNo()
        }

    }
}

@Composable
fun DisplayQuestion(
    start: Int,
    end: Int,
    data: QuestionItem?,
    selected: MutableState<String>,
    onNextClicked: () -> Unit
) {
    val isCorrectAnswer: (String) -> Boolean = remember(data) {
        {
            it == data?.answer
        }
    }
    val animate by animateFloatAsState(
        targetValue = start.toFloat() / end
    )
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                LinearProgressIndicator(
                    progress = animate,
                    modifier = Modifier
                        .then(
                            other = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .border(
                                    width = 2.dp, brush = Brush.linearGradient(
                                        listOf(Color.DarkGray, Color.DarkGray)
                                    ), shape = RoundedCornerShape(10.dp)
                                )
                        )
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(modifier = Modifier.fillMaxWidth(), text = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                        withStyle(style = SpanStyle(color = Color.Black, fontSize = 27.sp)) {
                            append("Question $start/")
                            withStyle(style = SpanStyle(color = Color.Black, fontSize = 10.sp)) {
                                append("$end")
                            }
                        }
                    }
                })
                DashedLine(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )

                Text(
                    text = data?.question.toString(),
                    style = MaterialTheme.typography.h5,
                    fontSize = 20.sp
                )
            }
            Column {
                CreateAnswersView(data, selected, isCorrectAnswer)
            }
            Column {
                CreateNextButton(selected, onNextClicked, context)
            }
        }
    }
}