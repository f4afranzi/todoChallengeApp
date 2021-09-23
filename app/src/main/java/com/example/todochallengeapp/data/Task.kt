package com.example.todochallengeapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Task(val text: String, val id: String, val creationDate: String) {

}