package com.example.smarthomegitops.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smarthomegitops.data.repository.GitHubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.smarthomegitops.domain.DeceptionDetector

class MainViewModel(
    private val repository: GitHubRepository = GitHubRepository(),
    private val detector: DeceptionDetector = DeceptionDetector()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Normal)

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        startPolling()
    }

    private fun startPolling() {
        viewModelScope.launch {
            while (isActive) {

                try {
                    val pullRequests = withContext(Dispatchers.IO) {
                        repository.getOpenPullRequests()
                    }

                    for (pullRequest in pullRequests) {

                        val comments = withContext(Dispatchers.IO) {
                            repository.getIssueComments(pullRequest.number)
                        }

                        for (comment in comments) {

                            val score = withContext(Dispatchers.Default) {
                                detector.analyze(comment.body ?: "")
                            }

                            if (score > 0) {
                                _uiState.value = UiState.SecurityAlert(
                                    confidence = score,
                                    rawText = comment.body ?: ""
                                )
                            }
                        }
                    }

                } catch (e: Exception) {
                    // Keep polling if a network or API error occurs.
                }

                delay(30_000)
            }
        }
    }
}