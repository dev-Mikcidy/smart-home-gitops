package com.example.smarthomegitops.data.model

data class UpdateFileRequest(
    val message: String,
    val content: String,
    val sha: String,
    val branch: String
)