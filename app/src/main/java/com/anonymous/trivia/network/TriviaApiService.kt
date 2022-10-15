package com.anonymous.trivia.network

import com.anonymous.trivia.model.Question
import retrofit2.http.GET

interface TriviaApiService {
    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/itmmckernan/triviaJSON/master/"
    }
    @GET("world.json")
    suspend fun getAllQuestions(): Question
}