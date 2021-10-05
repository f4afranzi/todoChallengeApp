package com.example.todochallengeapp.data

import kotlinx.serialization.Serializable

@Serializable
data class TaskRequestBody(val text: String) {}


