package com.example.smarthomegitops.presentation.viewmodel

sealed interface UiState {

    data object Normal : UiState

    data class SecurityAlert(
        val confidence: Int,
        val rawText: String
    ) : UiState
}