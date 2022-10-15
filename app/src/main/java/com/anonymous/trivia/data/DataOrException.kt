package com.anonymous.trivia.data

data class DataOrException<T, Boolean, E: Exception>(
    val data: T? = null,
    val loading: Boolean? = null,
    val exception: E? = null
)