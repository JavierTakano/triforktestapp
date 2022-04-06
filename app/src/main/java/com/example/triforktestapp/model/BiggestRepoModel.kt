package com.example.triforktestapp.model

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.triforktestapp.BiggestRepoQuery
import com.example.triforktestapp.apollo.ApolloInstance

class BiggestRepoModel {

    private lateinit var client: ApolloClient

    suspend fun getRepos(organization: String, cursor: String?): ApolloResponse<BiggestRepoQuery.Data> {
        client = ApolloInstance().get()
        return client.query(BiggestRepoQuery(organization, cursor)).execute()
    }
}