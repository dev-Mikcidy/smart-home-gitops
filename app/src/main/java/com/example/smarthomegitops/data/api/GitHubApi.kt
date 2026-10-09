package com.example.smarthomegitops.data.api
import com.example.smarthomegitops.data.model.PullRequest
import com.example.smarthomegitops.data.model.IssueComment
import com.example.smarthomegitops.data.model.PullRequestUpdate
import com.example.smarthomegitops.data.model.GitHubFile
import com.example.smarthomegitops.data.model.UpdateFileRequest

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path


interface GitHubApi {

    @GET("repos/dev-Mikcidy/smart-home-gitops/pulls")
    suspend fun getOpenPullRequests(
        @Header("Authorization") token: String
    ): List<PullRequest>

    @GET("repos/dev-Mikcidy/smart-home-gitops/issues/{issue_number}/comments")
    suspend fun getIssueComments(
        @Path("issue_number") issueNumber: Int,
        @Header("Authorization") token: String
    ): List<IssueComment>

    @GET("repos/dev-Mikcidy/smart-home-gitops/contents/house_config.json")
    suspend fun getHouseConfig(
        @Header("Authorization") token: String
    ): GitHubFile

    @PUT("repos/dev-Mikcidy/smart-home-gitops/contents/house_config.json")
    suspend fun updateHouseConfig(
        @Header("Authorization") token: String,
        @Body update: UpdateFileRequest
    ): retrofit2.Response<Unit>

    @PATCH("repos/dev-Mikcidy/smart-home-gitops/pulls/{pull_number}")
    suspend fun closePullRequest(
        @Path("pull_number") pullNumber: Int,
        @Header("Authorization") token: String,
        @Body update: PullRequestUpdate
    ): PullRequest
}