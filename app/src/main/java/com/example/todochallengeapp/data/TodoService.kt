package com.example.todochallengeapp.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

const val BASE_URL = "https://mighty-coast-90900.herokuapp.com"

interface TodoService {

    @GET("/tasks")
    fun showTodoListTasks(): Call<List<Task>>

    @POST("/tasks")
    fun addTaskToList(@Body taskRequestBody: TaskRequestBody): Call<Task>

    @PATCH("/tasks/{id}")
    fun updateTaskInList(@Path("id") id: String, @Body taskRequestBody: TaskRequestBody): Call<Unit>
}

