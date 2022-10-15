package com.anonymous.trivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anonymous.trivia.ui.theme.TriviaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.single

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TriviaViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication {
                CreateQuestions(viewModel)
            }
        }
    }
}


@Composable
private fun MyApplication(content: @Composable () -> Unit) {
    TriviaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TriviaTheme {
    }
}