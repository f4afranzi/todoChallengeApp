package com.example.todochallengeapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit.Builder
import java.util.UUID

val contentType = "application/json".toMediaType()

class TodoRepository {
    var retrofit = Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    var service: TodoService = retrofit.create(TodoService::class.java)

    fun getTasks(): Call<List<Task>> {
       return service.showTodoListTasks()
    }

    fun addTask(text: String): Call<Task> {
        return service.addTaskToList(TaskRequestBody(text))
    }

    fun updateTask(id: String, text: String): Call<Unit> {
        return service.updateTaskInList(id, TaskRequestBody(text))
    }
}