package com.anonymous.trivia.repository

import com.anonymous.trivia.data.DataOrException
import com.anonymous.trivia.model.QuestionItem
import com.anonymous.trivia.network.TriviaApiService
import javax.inject.Inject

class TriviaRepository @Inject constructor(private val apiService: TriviaApiService) {
    private var dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException = dataOrException.copy(loading = true)
            val questions = apiService.getAllQuestions()
            dataOrException =  dataOrException.copy(data = questions, loading = false)
        } catch (e: Exception) {
            dataOrException = dataOrException.copy(loading = false, exception = e)
        }
        return dataOrException
    }
}