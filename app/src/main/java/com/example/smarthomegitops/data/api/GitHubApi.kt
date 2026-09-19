package com.example.smarthomegitops.data.api
import com.example.smarthomegitops.data.model.PullRequest
import com.example.smarthomegitops.data.model.IssueComment
import retrofit2.http.Path

import retrofit2.http.GET
import retrofit2.http.Header

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
}