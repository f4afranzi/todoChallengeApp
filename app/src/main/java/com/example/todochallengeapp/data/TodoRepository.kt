package com.example.todochallengeapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit.Builder

val contentType = "application/json".toMediaType()

class TodoRepository {
    var retrofit = Builder()
        .baseUrl("https://mighty-coast-90900.herokuapp.com/")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    var service: TodoService = retrofit.create(TodoService::class.java)

    fun getTasks(): Call<List<Task>> {
       return service.showTodoListTasks()
    }
}