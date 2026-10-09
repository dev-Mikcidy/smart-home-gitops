
package com.example.smarthomegitops.data.repository
import com.example.smarthomegitops.BuildConfig
import com.example.smarthomegitops.data.api.RetrofitClient

import com.example.smarthomegitops.data.api.GitHubApi
import com.example.smarthomegitops.data.model.PullRequest
import com.example.smarthomegitops.data.model.IssueComment
import com.example.smarthomegitops.data.model.GitHubFile
import com.example.smarthomegitops.data.model.PullRequestUpdate
import com.example.smarthomegitops.data.model.UpdateFileRequest

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
    suspend fun closePullRequest(
        pullNumber: Int
    ): PullRequest {
        return api.closePullRequest(
            pullNumber,
            "Bearer ${BuildConfig.GITHUB_TOKEN}",
            PullRequestUpdate(state = "closed")
        )
    }

    suspend fun getHouseConfig(): GitHubFile {
        return api.getHouseConfig(
            "Bearer ${BuildConfig.GITHUB_TOKEN}"
        )
    }

    suspend fun updateHouseConfig(
        update: UpdateFileRequest
    ): retrofit2.Response<Unit> {
        return api.updateHouseConfig(
            "Bearer ${BuildConfig.GITHUB_TOKEN}",
            update
        )
    }
    
    suspend fun forceMerge(): retrofit2.Response<Unit> {

        val currentFile = getHouseConfig()

        val decodedContent = android.util.Base64
            .decode(currentFile.content, android.util.Base64.DEFAULT)
            .toString(Charsets.UTF_8)


        val updatedContent = decodedContent
            .replace(
                Regex("\"target_temperature\"\\s*:\\s*[-+]?\\d+(\\.\\d+)?"),
                "\"target_temperature\": 17.0"
            )
            .replace(
                "\"last_updated_by\": \"EcoAgent\"",
                "\"last_updated_by\": \"Android-Operator\""
            )

        val encodedContent = android.util.Base64
            .encodeToString(
                updatedContent.toByteArray(Charsets.UTF_8),
                android.util.Base64.NO_WRAP
            )

        val update = UpdateFileRequest(
            message = "Force merge by Android Operator",
            content = encodedContent,
            sha = currentFile.sha,
            branch = "main"
        )

        val response = updateHouseConfig(update)

        if (!response.isSuccessful) {
            throw java.io.IOException(
                "GitHub file update failed: HTTP ${response.code()}"
            )
        }

        return response
    }
}