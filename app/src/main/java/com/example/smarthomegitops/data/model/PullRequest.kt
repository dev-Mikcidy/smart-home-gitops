package com.example.smarthomegitops.data.model

data class PullRequest(
    val number: Int,
    val title: String,
    val body: String?
)