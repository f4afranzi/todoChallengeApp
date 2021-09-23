package com.example.todochallengeapp.data

import retrofit2.Call
import retrofit2.http.GET

private const val BASE_URL = "https://mighty-coast-90900.herokuapp.com/"

interface TodoService {
    @GET("tasks")
    fun showTodoListTasks(): Call<List<Task>>
}

