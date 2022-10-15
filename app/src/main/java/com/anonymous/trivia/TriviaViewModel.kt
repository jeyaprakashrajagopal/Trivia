package com.anonymous.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.trivia.data.DataOrException
import com.anonymous.trivia.model.QuestionItem
import com.anonymous.trivia.repository.TriviaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(private val repository: TriviaRepository) : ViewModel() {
    fun incrementQuestionNo() {
        _questionNo.value += 1
    }

    private val _questions: MutableStateFlow<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        MutableStateFlow(DataOrException())
    val questions = _questions.asStateFlow()

    private val _questionNo = MutableStateFlow<Int>(1)
    val questionNo = _questionNo.asStateFlow()

    init {
        viewModelScope.launch {
            _questions.value = _questions.value.copy(loading = true)
            val response = repository.getAllQuestions()
            if(!response.data.isNullOrEmpty()) {
                _questions.value = _questions.value.copy(data = response.data)
                _questions.value = _questions.value.copy(loading = false)
            } else {

            }
        }
    }
}