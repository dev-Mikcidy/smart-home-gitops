
package com.example.smarthomegitops.data.repository
import com.example.smarthomegitops.BuildConfig
import com.example.smarthomegitops.data.api.RetrofitClient

import com.example.smarthomegitops.data.api.GitHubApi
import com.example.smarthomegitops.data.model.PullRequest

import com.example.smarthomegitops.data.model.IssueComment

class GitHubRepository(
    private val api: GitHubApi = RetrofitClient.api
) {

    suspend fun getOpenPullRequests(): List<PullRequest> {
        return api.getOpenPullRequests("Bearer ${BuildConfig.GITHUB_TOKEN}")
    }

    suspend fun getIssueComments(
        issueNumber: Int
    ): List<IssueComment> {
        return api.getIssueComments(
            issueNumber,
            "Bearer ${BuildConfig.GITHUB_TOKEN}"
        )
    }
}