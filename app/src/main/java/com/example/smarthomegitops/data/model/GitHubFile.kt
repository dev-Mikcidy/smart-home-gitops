package com.example.smarthomegitops.data.model

data class GitHubFile(
    val name: String,
    val path: String,
    val sha: String,
    val content: String
)