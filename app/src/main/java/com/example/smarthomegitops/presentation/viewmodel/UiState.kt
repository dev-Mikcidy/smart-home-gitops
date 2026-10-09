package com.example.smarthomegitops.presentation.viewmodel

sealed interface UiState {

    data object Normal : UiState

    data class SecurityAlert(
        val confidence: Int,
        val rawText: String,
        val pullNumber: Int
    ) : UiState

    data class Error(
        val message: String
    ) : UiState
}