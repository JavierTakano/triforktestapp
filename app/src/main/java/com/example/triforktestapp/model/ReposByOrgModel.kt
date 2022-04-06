package com.example.triforktestapp.model

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.triforktestapp.ReposByOrgQuery
import com.example.triforktestapp.apollo.ApolloInstance

class ReposByOrgModel {

    private lateinit var client: ApolloClient

    suspend fun getReposForOrg(organization: String): ApolloResponse<ReposByOrgQuery.Data> {
        client = ApolloInstance().get()
        return client.query(ReposByOrgQuery(organization)).execute()
    }
}